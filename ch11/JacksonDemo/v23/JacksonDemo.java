import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;

import com.fasterxml.jackson.core.util.Instantiatable;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      JsonFactory factory = new JsonFactory();
      JsonGenerator generator = 
      factory.createGenerator(out, JsonEncoding.UTF8);
      generator.setPrettyPrinter(new MyPrettyPrinter());
      generator.writeStartObject();
      generator.writeStringField("firstname", "John");
      generator.writeStringField("lastName", "Doe");
      generator.writeNumberField("age", 42);
      generator.writeFieldName("address");
      generator.writeStartObject();
      generator.writeStringField("street", 
                                 "400 Some Street");
      generator.writeStringField("city", "Beverly Hills");
      generator.writeStringField("state", "CA");
      generator.writeNumberField("zipcode", 90210);
      generator.writeEndObject();
      generator.writeFieldName("phoneNumbers");
      generator.writeStartArray();
      generator.writeStartObject();
      generator.writeStringField("type", "home");
      generator.writeStringField("number", "310 555-1234");
      generator.writeEndObject();
      generator.writeStartObject();
      generator.writeStringField("type", "fax");
      generator.writeStringField("number", "310 555-4567");
      generator.writeEndObject();
      generator.writeEndArray();
      generator.writeEndObject();
      generator.close();
   }
}

class MyPrettyPrinter 
   implements PrettyPrinter, Instantiatable<MyPrettyPrinter>
{
   private final String LINE_SEP = 
      getProperty("line.separator");

   private int indent = 0;
   private boolean isNewline = true;

   @Override
   public MyPrettyPrinter createInstance()
   {
      return new MyPrettyPrinter();
   }

   @Override
   public void writeStartObject(JsonGenerator jg)
      throws IOException
   {
      if (!isNewline)
         newline(jg);
      jg.writeRaw('{');
      ++indent;
      isNewline = false;
   }

   @Override
   public void beforeObjectEntries(JsonGenerator jg)
      throws IOException
   {
      newline(jg);
   }

   @Override
   public void 
      writeObjectFieldValueSeparator(JsonGenerator jg)
      throws IOException
   {
      jg.writeRaw(" : ");
      isNewline = false;
   }

   @Override
   public void writeObjectEntrySeparator(JsonGenerator jg)
      throws IOException
   {
      jg.writeRaw(",");
      newline(jg);
   }

   @Override
   public void writeEndObject(JsonGenerator jg, 
                              int nrOfEntries)
      throws IOException
   {
      --indent;
      newline(jg);
      jg.writeRaw('}');
      isNewline = indent == 0;
   }

   @Override
   public void writeStartArray(JsonGenerator jg)
      throws IOException
   {
      newline(jg);
      jg.writeRaw("[");
      ++indent;
      isNewline = false;
   }

   @Override
   public void beforeArrayValues(JsonGenerator jg)
      throws IOException
   {
      newline(jg);
   }

   @Override
   public void writeArrayValueSeparator(JsonGenerator jg)
      throws IOException
   {
      jg.writeRaw(", ");
      isNewline = false;
   }

   @Override
   public void writeEndArray(JsonGenerator jg, 
                             int nrOfValues)
      throws IOException
   {
      --indent;
      newline(jg);
      jg.writeRaw(']');
      isNewline = false;
   }

   @Override
   public void writeRootValueSeparator(JsonGenerator jg)
      throws IOException
   {
      jg.writeRaw(' ');
   }

   private void newline(JsonGenerator jg) throws IOException
   {
      jg.writeRaw(LINE_SEP);
      for (int i = 0; i < indent; ++i)
         jg.writeRaw("  ");
      isNewline = true;
   }
}