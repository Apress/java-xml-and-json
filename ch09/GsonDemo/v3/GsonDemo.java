import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import static java.lang.System.*;

class Address
{
   private String street;
   private String city;
   private String state;
   private int zipcode;

   Address(String street, String city, String state, 
           int zipcode)
   {
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipcode = zipcode;
   }

   @Override
   public String toString()
   {
      return street + " " + city + " " + state + " " + 
             zipcode;
   }
}

public class GsonDemo
{
   public static void main(String[] args)
   {
      Gson gson = new Gson();
      Address address = new Address(null, "Beverly Hills", 
                                    "CA", 90210);
      JsonElement tree = gson.toJsonTree(address);
      out.println(tree);
      out.println(gson.fromJson(tree, Address.class));
      gson = new GsonBuilder().serializeNulls().create();
      tree = gson.toJsonTree(address);
      out.println(tree);
      out.println(gson.fromJson(tree, Address.class));
   }
}