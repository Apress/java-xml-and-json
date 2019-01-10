import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      String jsonStr =
      "{" +
      "\"firstName\": \"John\"," +
      "\"lastName\": \"Smith\"," +
      "\"isAlive\": true," +
      "\"age\": 25," +
      "\"address\":" +
      "{" +
      "\"streetAddress\": \"21 2nd Street\"," +
      "\"city\": \"New York\"," +
      "\"state\": \"NY\"," +
      "\"postalCode\": \"10021-3100\"" +
      "}," +
      "\"phoneNumbers\":" +
      "[" +
      "{" +
      "\"type\": \"home\"," +
      "\"number\": \"212 555-1234\"" +
      "}," +
      "{" +
      "\"type\": \"office\"," +
      "\"number\": \"646 555-4567\"" +
      "}" +
      "]," +
      "\"children\": []," +
      "\"spouse\": null" +
      "}";
      Json json = Json.read(jsonStr);
      out.println("Value = " + json.getValue());
      out.println();
      classify(json);
   }

   static void classify(Json jsonObject)
   {
      if (jsonObject.isArray())
         out.println("Array");
      else
      if (jsonObject.isBoolean())
         out.println("Boolean");
      else
      if (jsonObject.isNull())
         out.println("Null");
      else
      if (jsonObject.isNumber())
         out.println("Number");
      else
      if (jsonObject.isObject())
         out.println("Object");
      else
      if (jsonObject.isString())
         out.println("String");
      if (jsonObject.isPrimitive())
         out.println("Primitive");
   }
}