import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      List<String> planets = 
         Arrays.asList("Mercury", "Venus", "Terra", "Mars",
                       "Jupiter", "Saturn", "Uranus",
                       "Pluto");
      out.println(Json.createArrayBuilder(planets)
                      .remove(7)
                      .set(2, "Earth")
                      .add("Neptune")
                      .build());

      Map<String, Object> employees = new HashMap<>();
      employees.put("John Doe", 42);
      employees.put("Jill Smith", 38);
      employees.put("Bonnie Barnes", 26);
      JsonObject employees_ = 
         Json.createObjectBuilder(employees)
             .remove("Jill Smith")
             .build();
      out.println(employees_);
   }
}