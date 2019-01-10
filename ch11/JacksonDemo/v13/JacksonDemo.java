import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      mapper.disable(JsonGenerator.Feature.
                     AUTO_CLOSE_TARGET);
      Person1 person1 = new Person1();
      mapper.writeValue(out, person1);
      out.println();
      Person2 person2 = new Person2();
      mapper.writeValue(out, person2);
      out.println();
      Person3 person3 = new Person3();
      mapper.writeValue(out, person3);
      out.println();
      Person4 person4 = new Person4();
      mapper.writeValue(out, person4);
   }
}

class Person1
{
   public int personID = 0;
   public String firstName = null;
   public String lastName = "Doe";
   public List<String> phoneNumbers = new ArrayList<>();
}

@JsonInclude(JsonInclude.Include.ALWAYS)
class Person2
{
   public int personID = 0;
   public String firstName = null;
   public String lastName = "Doe";
   public List<String> phoneNumbers = new ArrayList<>();
}

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class Person3
{
   public int personID = 0;
   public String firstName = null;
   public String lastName = "Doe";
   public List<String> phoneNumbers = new ArrayList<>();
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Person4
{
   public int personID = 0;
   public String firstName = null;
   public String lastName = "Doe";
   public List<String> phoneNumbers = new ArrayList<>();
}