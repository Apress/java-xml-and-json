import javax.json.Json;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java JSONPDemo pointer");
         return;
      }
      String encPointer = Json.encodePointer(args[0]);
      out.printf("Encoded pointer: %s%n\n", encPointer);
      out.printf("Decoded pointer: %s%n\n", 
                 Json.decodePointer(encPointer));
   }
}