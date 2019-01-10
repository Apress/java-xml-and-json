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
      Person2 person2 = mapper.readValue(jsonContent, 
                                         Person2.class);
      out.println(person2);
   }
}

class Person
{
   private int personID = 0;
   private String firstName = null;

   public int getPersonID()
   {
      return personID;
   }

   @JsonSetter("id")
   public void setPersonID(int personID)
   {
      this.personID = personID;
   }

   public String getFirstName()
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

class Person2
{
   @JsonSetter("id")
   private int personID = 0;
   private String firstName = null;

   public int getPersonID()
   {
      return personID;
   }

   public String getFirstName()
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