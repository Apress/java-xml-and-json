import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      JsonObject person =
         Json.createObjectBuilder()
             .add("firstName", "John")
             .add("lastName", "Doe")
             .add("age", 42)
             .add("address", Json.createObjectBuilder()
                                 .add("street", 
                                      "400 Some Street")
                                 .add("city", 
                                      "Beverly Hills")
                                 .add("state", "CA")
             .add("zipcode", 90210))
             .add("phoneNumbers", 
                  Json.createArrayBuilder()
                      .add(Json.createObjectBuilder()
                               .add("type", "home")
                               .add("number", 
                                    "310 555-1234"))
                             .add(Json.createObjectBuilder()
                                      .add("type", "fax")
                                      .add("number", 
                                           "310 555-4567")))
             .build();
      out.printf("First name: %s%n", 
                 person.getString("firstName"));
      out.printf("Last name: %s%n", 
                 person.getString("lastName"));
      out.printf("Age: %d%n", person.getInt("age"));
      out.println("Address");
      out.println("-------");
      JsonObject address = person.getJsonObject("address");
      out.printf("   Street: %s%n", 
                 address.getString("street"));
      out.printf("   City: %s%n", 
                 address.getString("city"));
      out.printf("   State: %s%n", 
                 address.getString("state"));
      out.printf("   Zipcode: %d%n", 
                 address.getInt("zipcode"));
      out.println("Phone Numbers");
      out.println("-------------");
      JsonArray phoneNumbers = 
         person.getJsonArray("phoneNumbers");
      for (JsonObject phoneNumber: 
           phoneNumbers.getValuesAs(JsonObject.class))
      {
         out.printf("   Type: %s%n", 
                    phoneNumber.getString("type"));
         out.printf("   Number: %s%n", 
                    phoneNumber.getString("number"));
      }
   }
}