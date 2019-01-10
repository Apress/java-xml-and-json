import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static java.lang.System.*;

public class RunScript
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java RunScript script");
         return;
      }
      ScriptEngineManager manager = 
         new ScriptEngineManager();
      ScriptEngine engine = 
         manager.getEngineByName("nashorn");
      try
      {
         engine.eval(new FileReader(args[0]));
      }
      catch (ScriptException se)
      {
         err.println(se.getMessage());
      }
      catch (IOException ioe)
      {
         err.println(ioe.getMessage());
      }      
   }
}