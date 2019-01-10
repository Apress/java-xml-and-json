import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      JsonFactory factory = new JsonFactory();
      JsonParser parser = 
         factory.createParser(new File("person.json"));
      while (!parser.isClosed())
      {
         JsonToken jsonToken = parser.nextToken();
         if (jsonToken == null)
            break;
         out.printf("jsonToken = %s [%s] [%b] [%s]%n", 
                    jsonToken, jsonToken.asString(), 
                    jsonToken.isNumeric(),
                    parser.getValueAsString());
      }
   }
}