import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import javax.json.stream.JsonCollectors;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      JsonArray employees;
      employees = Json.createArrayBuilder()
                      .add(Json.createObjectBuilder()
                               .add("name", "John")
                               .add("age", 42))
                      .add(Json.createObjectBuilder()
                               .add("name", "Joan")
                               .add("age", 28))
                      .add(Json.createObjectBuilder()
                               .add("name", "Trevor")
                               .add("age", 35))
                      .add(Json.createObjectBuilder()
                               .add("name", "Sally")
                               .add("age", 49))
                      .add(Json.createObjectBuilder()
                               .add("name", "Frank")
                               .add("age", 26))
                      .build();
      out.println(employees);
      out.println();
      JsonObject result =
      employees.getValuesAs(JsonObject.class)
               .stream()
               .filter(x -> x.getInt("age") > 40)
               .collect(JsonCollectors.
                        toJsonObject(x -> 
                                     x.asJsonObject()
                                      .getString("name"),
                                     x -> 
                                     x.asJsonObject()
                                      .get("age")));
      out.println(result);
   }
}