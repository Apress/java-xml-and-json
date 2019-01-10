import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;

import javax.json.stream.JsonGenerator;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      try (var fw = new FileWriter("person.json"))
      {
         JsonGenerator generator = Json.createGenerator(fw);
         generator.writeStartObject()
                     .write("firstName", "John")
                     .write("lastName", "Doe")
                     .write("age", 42)
                     .writeStartObject("address")
                        .write("street", "400 Some Street")
                        .write("city", "Beverly Hills")
                        .write("state", "CA")
                        .write("zipcode", 90210)
                     .writeEnd()
                     .writeStartArray("phoneNumbers")
                        .writeStartObject()
                           .write("type", "home")
                           .write("number", "310 555-1234")
                        .writeEnd()
                        .writeStartObject()
                           .write("type", "fax")
                           .write("number", "310 555-4567")
                        .writeEnd()
                     .writeEnd()
                  .writeEnd();
         generator.close();
      }
      catch (IOException ioe)
      {
         out.printf("I/O error: %s%n", ioe.getMessage());
      }
   }
}