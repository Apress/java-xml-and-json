import java.net.URI;
import java.net.URISyntaxException;

import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   final static String SCHEMA_URI =
      "http://javajeff.ca/schema.json";

   public static void main(String[] args) 
      throws URISyntaxException
   {
      Json.Schema schema = Json.schema(new URI(SCHEMA_URI));
      Json json = Json.read("{\"name\": \"John Doe\", " +
                            "\"age\": 35}");
      out.println(schema.validate(json));
      json = Json.read("{\"name\": \"John Doe\", " +
                       "\"age\": 65}");
      out.println(schema.validate(json));
      json = Json.read("{\"name\": \"John Doe\", " + 
                       "\"age\": \"35\"}");
      out.println(schema.validate(json));
      json = Json.read("{\"age\": 35}");
      out.println(schema.validate(json));
   }
}