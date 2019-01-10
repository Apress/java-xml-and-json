import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = 
         mapper.readTree(new File("person.json"));
      out.printf("firstName = %s%n", 
                 rootNode.get("firstName"));
      out.printf("lastName = %s%n", 
                 rootNode.get("lastName"));
      out.printf("age = %s%n", rootNode.get("age"));
      JsonNode address = rootNode.get("address");
      out.println("address");
      out.printf("   street = %s%n", address.get("street"));
      out.printf("   city = %s%n", address.get("city"));
      out.printf("   state = %s%n", address.get("state"));
      out.printf("   zipcode = %s%n", 
                 address.get("zipcode"));
      JsonNode phoneNumbers = rootNode.get("phoneNumbers");
      out.println("phoneNumbers");
      for (int i = 0; i < phoneNumbers.size(); i++)
      {
         out.printf("   %d: ", i);
         JsonNode phoneNumber = phoneNumbers.get(i);
         out.printf("type = %s, number = %s%n",
                    phoneNumber.get("type"),
                    phoneNumber.get("number"));
      }
      out.println();
      out.printf("%s%n", rootNode.with("address").
                                  get("street"));
      out.printf("%s%n", rootNode.withArray("phoneNumbers").
                                  get(1).get("type"));
   }
}