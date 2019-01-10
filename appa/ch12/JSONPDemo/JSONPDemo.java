import java.io.FileReader;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         err.println("usage: java JSONPDemo jsonfile " +
                     "pointer");
         return;
      }
      try (var fr = new FileReader(args[0]))
      {
         JsonReader reader = Json.createReader(fr);
         JsonObject o = (JsonObject) reader.read();
         JsonPointer ptr = Json.createPointer(args[1]);
         out.println(ptr.getValue(o));
      }
      catch (IOException ioe)
      {
         err.printf("I/O error: %s%n", ioe.getMessage());
      }
   }
}