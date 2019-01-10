import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import static java.lang.System.*;

/*
   JsonPatch Demonstration:

   1) Create the following JSON document:

   {
      "firstName": "John",
      "lastName": "Doe",
      "age": 42,
      "address": 
      {
         "streetAddress": "400 Some Street",
         "city": "Beverly Hills",
         "state": "CA",
         "zipcode": 90210
      },
      "phoneNumbers":
      [
         {
            "type": "home",
            "number": "310 555-1234"
         },
         {
            "type": "fax",
            "number": "310 555-4567" 
         }
      ] 
   }

   2) Use JsonPatch to convert the previous JSON document
      to the following JSON document:

   {
      "firstName": "John",
      "lastName": "Doe",
      "age": 30,
      "address": 
      {
         "streetAddress": "400 Some Street",
         "city": "Beverly Hills",
         "state": "CA",
         "zipcode": 90210
      },
      "phoneNumbers":
      [
         {
            "type": "fax",
            "number": "310 555-4567" 
         },
         {
            "type": "cell",
            "number": "123 456-7890"
         }
      ] 
   }
*/

public class JSONPDemo
{
   public static void main(String[] args)
   {
      JsonObject person;
      person = 
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
      String patch =
      "[" +
      "{" +
      "\"op\": \"replace\"," +
      "\"path\": \"/age\"," +
      "\"value\": 30" +
      "}," +
      "{" +
      "\"op\": \"add\"," +
      "\"path\": \"/phoneNumbers/-\"," +
      "\"value\": " +
      "{" +
      "\"type\": \"cell\"," +
      "\"number\": \"123 456-7890\"" +
      "}" +
      "}," +
      "{" +
      "\"op\": \"remove\"," +
      "\"path\": \"/phoneNumbers/0\"" +
      "}" +
      "]";
      JsonArray patchArray = 
         Json.createReader(new StringReader(patch))
             .readArray();
      person = Json.createPatch(patchArray).apply(person);
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