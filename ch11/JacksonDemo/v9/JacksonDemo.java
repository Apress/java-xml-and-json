import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      String jsonContent =
      "{" +
      "   \"id\": 820787," +
      "   \"firstName\": \"Pierre\"," +
      "   \"lastName\": \"Francois\"" +
      "}";
      ObjectMapper mapper = new ObjectMapper();
      PropContainer pc = 
         mapper.readValue(jsonContent, PropContainer.class);
      Iterator<Map.Entry<String, Object>> iter = 
         pc.iterator();
      while (iter.hasNext())
      {
         Map.Entry<String, Object> entry = iter.next();
         out.printf("Key: %s, Value: %s%n", entry.getKey(), 
                    entry.getValue());
      }
   }
}

class PropContainer
{
   // public String lastName;

   private Map<String, Object> properties;

   PropContainer()
   {
      properties = new HashMap<>();
   }

   @JsonAnySetter
   void addProperty(String fieldName, Object value)
   {
      properties.put(fieldName, value);
   }

   Iterator<Map.Entry<String, Object>> iterator()
   {
      return properties.entrySet().iterator();
   }
}