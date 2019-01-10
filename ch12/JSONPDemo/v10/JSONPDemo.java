import javax.json.Json;
import javax.json.JsonObject;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      JsonObject person1;
      person1 = 
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
      JsonObject person2;
      person2 = 
         Json.createObjectBuilder()
             .add("firstName", "John")
             .add("lastName", "Doe")
             .add("age", 30)
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
                               .add("type", "fax")
                               .add("number", 
                                    "310 555-4567"))
                      .add(Json.createObjectBuilder()
                               .add("type", "cell")
                               .add("number", 
                                    "123 456-7890")))
             .build();
      out.println(Json.createMergeDiff(person1, person2)
                      .toJsonValue());
   }
}