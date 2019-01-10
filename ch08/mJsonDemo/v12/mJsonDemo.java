import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      String jsonStr =
      "{" +
      "\"firstName\": \"John\"," +
      "\"lastName\": \"Doe\"," +
      "\"courses\":" +
      "[\"English\", \"French\", \"Spanish\"]" +
      "}";
      Json json = Json.read(jsonStr);
      out.println(json);
      out.println();
      json.delAt("lastName");
      out.println(json);
      out.println();
      json.at("courses").delAt(1);
      out.println(json);
   }
}