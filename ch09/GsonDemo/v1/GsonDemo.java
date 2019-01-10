import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.lang.System.*;

public class GsonDemo
{
   public static void main(String[] args)
   {
      Gson gson = new Gson();
      String name = gson.fromJson("\"John Doe\"", 
                                  String.class);
      out.println(name);
      gson.toJson(256, out);
      out.println();
      gson.toJson("<html>", out);
      out.println();
      gson = new GsonBuilder().disableHtmlEscaping().create();
      gson.toJson("<html>", out);
      out.println();
   }
}