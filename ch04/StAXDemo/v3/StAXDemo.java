import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import static java.lang.System.*;

public class StAXDemo
{
   final static String NS1 = "http://www.w3.org/1999/xhtml";
   final static String NS2 = "http://www.javajeff.ca/";

   public static void main(String[] args)
   {
      try
      {
         XMLOutputFactory xmlof = 
            XMLOutputFactory.newFactory();
         FileWriter fw = new FileWriter("recipe.xml");
         XMLStreamWriter xmlsw =
            xmlof.createXMLStreamWriter(fw);
         xmlsw.writeStartDocument();
         xmlsw.setPrefix("h", NS1);
         xmlsw.writeStartElement(NS1, "html");
         xmlsw.writeNamespace("h", NS1);
         xmlsw.writeNamespace("r", NS2);
         xmlsw.writeStartElement(NS1, "head");
         xmlsw.writeStartElement(NS1, "title");
         xmlsw.writeCharacters("Recipe");
         xmlsw.writeEndElement();
         xmlsw.writeEndElement();
         xmlsw.writeStartElement(NS1, "body");
         xmlsw.setPrefix("r", NS2);
         xmlsw.writeStartElement(NS2, "recipe");
         xmlsw.writeStartElement(NS2, "title");
         xmlsw.writeCharacters("Grilled Cheese Sandwich");
         xmlsw.writeEndElement();
         xmlsw.writeStartElement(NS2, "ingredients");
         xmlsw.setPrefix("h", NS1);
         xmlsw.writeStartElement(NS1, "ul");
         xmlsw.writeStartElement(NS1, "li");
         xmlsw.setPrefix("r", NS2);
         xmlsw.writeStartElement(NS2, "ingredient");
         xmlsw.writeAttribute("qty", "2");
         xmlsw.writeCharacters("bread slice");
         xmlsw.writeEndElement();
         xmlsw.setPrefix("h", NS1);
         xmlsw.writeEndElement();
         xmlsw.writeEndElement();
         xmlsw.setPrefix("r", NS2);
         xmlsw.writeEndElement();
         xmlsw.writeEndDocument();
         xmlsw.flush();
         xmlsw.close();
      }
      catch (FactoryConfigurationError fce)
      {
         err.printf("FCE: %s%n", fce.toString());
      }
      catch (IOException ioe)
      {
         err.printf("IOE: %s%n", ioe.toString());
      }
      catch (XMLStreamException xmlse)
      {
         err.printf("XMLSE: %s%n", xmlse.toString());
      }
   }
}