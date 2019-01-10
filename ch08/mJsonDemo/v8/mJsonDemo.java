import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      String jsonStr =
      "{" +
      "\"propName\": \"propValue\"," +
      "\"propArray\":" +
      "[" +
      "{" +
      "\"element1\": \"value1\"" +
      "}," +
      "{" +
      "\"element2\": \"value2\"" +
      "}" +
      "]" +
      "}";
      Json json = Json.read(jsonStr);
      Json jsonElement1 = json.at("propArray").at(0);
      out.println(jsonElement1);
      out.println();
      out.println(jsonElement1.up());
      out.println();
      out.println(jsonElement1.up().up());
      out.println();
      out.println(jsonElement1.up().up().up());
   }
}