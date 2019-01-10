import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      String jsonContent =
      "{" +
      "   \"make\": \"Ford\"," +
      "   \"model\": \"F150\"," +
      "   \"year\": 2008" +
      "}";
      ObjectMapper mapper = new ObjectMapper();
      Vehicle vehicle = mapper.readValue(jsonContent, 
                                         Vehicle.class);
      out.printf("Make %s, Model %s, Year %d%n", 
                 vehicle.getMake(), vehicle.getModel(), 
                 vehicle.getYear());
   }
}

class Vehicle
{
   private String make, model;

   private int year;

   @JsonCreator
   Vehicle(@JsonProperty("make") String make, 
           @JsonProperty("model") String model, 
           @JsonProperty("year") int year)
   {
      this.make = make;
      this.model = model;
      this.year = year;
   }

   String getMake()
   {
      return make;
   }

   String getModel()
   {
      return model;
   }

   int getYear()
   {
      return year;
   }
}