import java.io.File;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      Props1 p1 = new Props1();
      mapper.writeValue(new File("props1.json"), p1);
      Props2 p2 = new Props2();
      mapper.writeValue(new File("props2.json"), p2);
      out.println("serialization successful");
   }
}

class Props1
{
   public String a = "A";
   public String b = "B";

   @JsonValue
   public String toJSON()
   {
      return a + "\"-\"" + b;
   }
}

class Props2
{
   private Map<String, String> props = new HashMap<>();

   Props2()
   {
      props.put("a", "A'A'\"A\"");
      props.put("b", "B'B'\"B\"");
   }

   @JsonValue
   public Map<String, String> toJSON()
   {
      return props;
   }
}