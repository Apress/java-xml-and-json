import java.io.File;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      SomeClass1 sc1 = new SomeClass1(1, 2, 3);
      mapper.writeValue(new File("sc1.json"), sc1);
      SomeClass2 sc2 = new SomeClass2(1, 2, 3);
      mapper.writeValue(new File("sc2.json"), sc2);
      SomeClass3 sc3 = new SomeClass3(1, 2, 3);
      mapper.writeValue(new File("sc3.json"), sc3);
      sc1 = mapper.readValue(new File("sc1.json"), 
                             SomeClass1.class);
      sc1.print();
      sc2 = mapper.readValue(new File("sc2.json"), 
                             SomeClass2.class);
      sc2.print();
      sc3 = mapper.readValue(new File("sc3.json"), 
                             SomeClass3.class);
      sc3.print();
   }
}

class SomeClass1
{
   public int a;
   private int b;
   protected int c;

   SomeClass1()
   {
   }

   SomeClass1(int a, int b, int c)
   {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public void print()
   {
      out.printf("a = %d, b = %d, c = %d%n", a, b, c);
   }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility
                                  .PROTECTED_AND_PUBLIC)
class SomeClass2
{
   public int a;
   private int b;
   protected int c;

   SomeClass2()
   {
   }

   SomeClass2(int a, int b, int c)
   {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public void print()
   {
      out.printf("a = %d, b = %d, c = %d%n", a, b, c);
   }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility
                                  .ANY)
class SomeClass3
{
   public int a;
   private int b;
   protected int c;

   SomeClass3()
   {
   }

   SomeClass3(int a, int b, int c)
   {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public void print()
   {
      out.printf("a = %d, b = %d, c = %d%n", a, b, c);
   }
}