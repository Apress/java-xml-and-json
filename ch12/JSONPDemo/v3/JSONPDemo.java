import java.io.StringReader;

import javax.json.Json;

import javax.json.stream.JsonParser;

import static javax.json.stream.JsonParser.Event;

import static java.lang.System.*;

public class JSONPDemo
{
   public static void main(String[] args)
   {
      String json = 
      "{" +
      "   \"firstName\": \"John\"," +
      "   \"lastName\": \"Doe\"," +
      "   \"age\": 42," +
      "   \"address\":" + 
      "   {" +
      "      \"street\": \"400 Some Street\"," +
      "      \"city\": \"Beverly Hills\"," +
      "      \"state\": \"CA\"," +
      "      \"zipcode\": 90210" +
      "   }," +
      "   \"phoneNumbers\":" +
      "   [" +
      "      {" +
      "         \"type\": \"home\"," +
      "         \"number\": \"310 555-1234\"" +
      "      }," +
      "      {" +
      "         \"type\": \"fax\"," +
      "         \"number\": \"310 555-4567\"" +
      "      }" +
      "   ]" +
      "}";
      JsonParser parser = 
         Json.createParser(new StringReader(json));
      while (parser.hasNext())
      {
         Event event = parser.next();
         if (event == Event.KEY_NAME)
            switch (parser.getString())
            {
               case "firstName":
                  parser.next();
                  out.printf("First name: %s%n", 
                             parser.getString());
                  break;

               case "lastName":
                  parser.next();
                  out.printf("Last name: %s%n", 
                             parser.getString());
                  break;

               case "age":
                  parser.next();
                  out.printf("Age: %d%n", parser.getInt());
                  break;

               case "address":
                  parser.next();
                  out.println("Address");
                  out.println("-------");
                  break;

               case "street":
                  parser.next();
                  out.printf("   Street: %s%n", 
                             parser.getString());
                  break;

               case "city":
                  parser.next();
                  out.printf("   City: %s%n", 
                             parser.getString());
                  break;

               case "state":
                  parser.next();
                  out.printf("   State: %s%n", 
                             parser.getString());
                  break;

               case "zipcode":
                  parser.next();
                  out.printf("   Zipcode: %d%n", 
                             parser.getInt());
                  break;

               case "phoneNumbers":
                  parser.next();
                  out.println("Phone Numbers");
                  out.println("-------------");
                  break;

               case "type":
                  parser.next();
                  out.printf("   Type: %s%n", 
                             parser.getString());
                  parser.next();
                  parser.next();
                  out.printf("   Number: %s%n", 
                             parser.getString());
                  break;
            }
      }
   }
}