import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mjson.Json;

import static java.lang.System.*;

public class mJsonDemo
{
   public static void main(String[] args)
   {
      List<String> weekdays = 
         Arrays.asList("Sunday", "Monday", "Tuesday", 
                       "Wednesday", "Thursday", "Friday", 
                       "Saturday");
      out.println(Json.make(weekdays));

      Map<String, Number> people = new HashMap<>();
      people.put("John", 33);
      people.put("Joan", 27);
      out.println(Json.make(people));

      Map<String, String[]> planets = new HashMap<>();
      planets.put("Mercury", null);
      planets.put("Earth", new String[] {"Luna"});
      planets.put("Mars", new String[] {"Phobos", 
                                        "Deimos"});
      out.println(Json.make(planets));
   }
}