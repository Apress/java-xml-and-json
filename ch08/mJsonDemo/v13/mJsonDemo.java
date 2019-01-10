import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      String jsonStr =
      "{" +
      "\"firstName\": \"John\"," +
      "\"courses\":" +
      "[\"English\"]" +
      "}";
      Json json = Json.read(jsonStr);
      out.println(json);
      out.println();
      Json jsono = 
         Json.read("{\"initial\": \"P\", \"lastName\": " +
                   "\"Doe\"}");
      Json jsona = Json.read("[\"French\", \"Spanish\"]");
      json.with(jsono);
      out.println(json);
      out.println();
      json.at("courses").with(jsona);
      out.println(json);
   }
}