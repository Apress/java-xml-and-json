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
      out.printf("First name = %s%n", json.at("firstName"));
      out.printf("Last name = %s%n", json.at("lastName"));
      out.printf("Is alive = %s%n", json.at("isAlive"));
      out.printf("Age = %d%n", json.at("age").asInteger());
      out.println("Address");
      Json jsonAddr = json.at("address");
      out.printf("   Street address = %s%n", 
                 jsonAddr.at("streetAddress"));
      out.printf("   City = %s%n", jsonAddr.at("city"));
      out.printf("   State = %s%n", jsonAddr.at("state"));
      out.printf("   Postal code = %s%n",
                 jsonAddr.at("postalCode"));
      out.println("Phone Numbers");
      Json jsonPhone = json.at("phoneNumbers");
      out.printf("   Type = %s%n", jsonPhone.at(0).
                 at("type"));
      out.printf("   Number = %s%n", jsonPhone.at(0).
                 at("number"));
      out.println();
      out.printf("   Type = %s%n", jsonPhone.at(1).
                 at("type"));
      out.printf("   Number = %s%n", jsonPhone.at(1).
                 at("number"));
      Json jsonChildren = json.at("children");
      out.printf("Children = %s%n", jsonChildren);
      out.printf("Spouse = %s%n", json.at("spouse"));
   }
}