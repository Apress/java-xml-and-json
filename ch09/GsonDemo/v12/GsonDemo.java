import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

public class GsonDemo
{
   static 
   class Vehicle<T>
   {
      T vehicle;

      T get()
      {
         return vehicle;
      }

      void set(T vehicle)
      {
         this.vehicle = vehicle;
      }

      @Override
      public String toString()
      {
         out.printf("Class of vehicle: %s%n", 
                    vehicle.getClass());
         return "Vehicle: " + vehicle.toString();
      }
   }

   static
   class Truck
   {
      String make;
      String model;

      Truck(String make, String model)
      {
         this.make = make;
         this.model = model;
      }

      @Override
      public String toString()
      {
         return "Make: " + make + " Model: " + model;
      }
   }

   public static void main(String[] args)
   {
      Gson gson = new Gson();

      // ...

      out.printf("PART 1%n");
      out.printf("------%n%n");

      List<String> weekdays = 
         Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", 
                       "Fri", "Sat");
      String json = gson.toJson(weekdays);
      out.printf("%s%n%n", json);
      try
      {
         out.printf("%s%n%n", 
                    gson.fromJson(json, 
                                  weekdays.getClass()));
      }
      catch (ClassCastException cce)
      {
         cce.printStackTrace();
         out.println();
      }
      Type listType = 
         new TypeToken<List<String>>() {}.getType();
      out.printf("Type = %s%n%n", listType);
      try
      {
         out.printf("%s%n%n", gson.fromJson(json, 
                                            listType));
      }
      catch (ClassCastException cce)
      {
         cce.printStackTrace();
         out.println();
      }
      out.printf("%s%n%n", (List) gson.fromJson(json, 
                                                listType));

      // ...

      out.printf("PART 2%n");
      out.printf("------%n%n");
   
      Truck truck = new Truck("Ford", "F150");
      Vehicle<Truck> vehicle = new Vehicle<>();
      vehicle.set(truck);

      json = gson.toJson(vehicle);
      out.printf("%s%n%n", json);
      out.printf("%s%n%n", 
                 gson.fromJson(json, vehicle.getClass()));

      // ...

      out.printf("PART 3%n");
      out.printf("------%n%n");

      Map<String, String> map = 
         new HashMap<String, String>() 
      {
         {
            put("key", "value");
         }
      };
      out.printf("Map = %s%n%n", map);
      out.printf("%s%n%n", gson.toJson(map));
      out.printf("%s%n%n", gson.fromJson(gson.toJson(map), 
                                         map.getClass()));

      // ...

      out.printf("PART 4%n");
      out.printf("------%n%n");

      Type vehicleType = 
         new TypeToken<Vehicle<Truck>>() {}.getType();
      json = gson.toJson(vehicle, vehicleType);
      out.printf("%s%n%n", json);
      out.printf("%s%n%n", (Vehicle) 
                           gson.fromJson(json, 
                                         vehicleType));

      Type mapType = 
         new TypeToken<Map<String,String>>() {}.getType();
      out.printf("%s%n%n", gson.toJson(map, mapType));
      out.printf("%s%n%n", (Map) 
                 gson.fromJson(gson.toJson(map, mapType), 
                               mapType));
   }
}