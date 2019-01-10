import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      String jsonStr =
      "{" +
      "\"name\": null," +
      "\"courses\":" +
      "[null]" +
      "}";
      Json json = Json.read(jsonStr);
      out.println(json);
      out.println();
      json.set("name", "John Doe");
      Json jsonCourses = json.at("courses");
      jsonCourses.set(0, "English");
      out.println(json);
   }
}