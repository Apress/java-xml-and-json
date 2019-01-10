import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      String jsonContent =
      "{" +
      "   \"color\": \"black\"" +
      "}";

      Canvas canvas = 
         new ObjectMapper().readerFor(Canvas.class)
                           .readValue(jsonContent);
      out.printf("Color = %s%n", canvas.color);
      new ObjectMapper().writeValue(new File("color.json"), 
                                    canvas);
   }
}

enum Color
{
   BLACK, UNKNOWN
}

class Canvas
{
   @JsonDeserialize(using = ColorDeserializer.class)
   @JsonSerialize(using = ColorSerializer.class)
   public Color color;
}

class ColorDeserializer extends JsonDeserializer<Color>
{
   @Override
   public Color deserialize(JsonParser jsonParser,
                            DeserializationContext 
                            deserializationContext)
      throws IOException, JsonProcessingException
   {
      switch (jsonParser.getText().toLowerCase())
      {
         case "black":
            return Color.BLACK;

         default:
            return Color.UNKNOWN;
      }
   }
}

class ColorSerializer extends JsonSerializer<Color>
{
   @Override
   public void serialize(Color color, 
                         JsonGenerator jsonGenerator,
                         SerializerProvider 
                         serializerProvider)
      throws IOException, JsonProcessingException
   {
      switch (color)
      {
         case BLACK:
            jsonGenerator.writeString("black");
            break;

         default:
            jsonGenerator.writeString("unknown");
      }
   }
}