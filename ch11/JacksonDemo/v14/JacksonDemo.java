import java.io.File;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      String jsonContent =
      "{" +
      "   \"id\": 820787," +
      "   \"firstName\": \"Pierre\"" +
      "}";
      ObjectMapper mapper = new ObjectMapper();
      Person person = mapper.readValue(jsonContent, 
                                       Person.class);
      out.println(person);
      mapper.writeValue(new File("pierre.json"), person);
   }
}

class Person
{
   private int personID = 0;
   private String firstName = null;

   @JsonGetter("id")
   public int personID()
   {
      return personID;
   }

   public void setPersonID(int personID)
   {
      this.personID = personID;
   }

   @JsonGetter("firstName")
   public String firstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @Override
   public String toString()
   {
      return personID + ": " + firstName;
   }
}