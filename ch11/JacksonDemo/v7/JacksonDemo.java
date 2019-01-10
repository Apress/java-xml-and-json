import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      Person person = 
         mapper.readValue(new File("person.json"), 
                          Person.class);
      out.println(person);
   }
}