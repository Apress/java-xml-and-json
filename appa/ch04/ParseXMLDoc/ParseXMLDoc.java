import java.io.FileReader;
import java.io.IOException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static java.lang.System.*;

public class ParseXMLDoc
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java ParseXMLDoc pathname");
         return;
      }
      XMLInputFactory xmlif = XMLInputFactory.newFactory();
      XMLStreamReader xmlsr = null;
      try (var fr = new FileReader(args[0]))
      {
         xmlsr = xmlif.createXMLStreamReader(fr);
         int item = xmlsr.getEventType();
         if (item != XMLStreamReader.START_DOCUMENT)
         {
            err.println("START_DOCUMENT expected");
            return;
         }
         while ((item = xmlsr.next()) != 
                XMLStreamReader.END_DOCUMENT)
            switch (item)
            {
               case XMLStreamReader.ATTRIBUTE:
                  out.println("ATTRIBUTE");
                  break;
               case XMLStreamReader.CDATA:
                  out.println("CDATA");
                  break;
               case XMLStreamReader.CHARACTERS:
                  out.println("CHARACTERS");
                  break;
               case XMLStreamReader.COMMENT:
                  out.println("COMMENT");
                  break;
               case XMLStreamReader.DTD:
                  out.println("DTD");
                  break;
               case XMLStreamReader.END_ELEMENT:
                  out.println("END_ELEMENT");
                  break;
               case XMLStreamReader.ENTITY_DECLARATION:
                  out.println("ENTITY_DECLARATION");
                  break;
               case XMLStreamReader.ENTITY_REFERENCE:
                  out.println("ENTITY_REFERENCE");
                  break;
               case XMLStreamReader.NAMESPACE:
                  out.println("NAMESPACE");
                  break;
               case XMLStreamReader.NOTATION_DECLARATION:
                  out.println("NOTATION_DECLARATION");
                  break;
               case XMLStreamReader.PROCESSING_INSTRUCTION:
                  out.println("PROCESSING_INSTRUCTION");
                  break;
               case XMLStreamReader.SPACE:
                  out.println("SPACE");
                  break;
               case XMLStreamReader.START_ELEMENT:
                  out.println("START_ELEMENT");
                  out.printf("Name = %s%n", 
                             xmlsr.getName());
                  out.printf("Local name = %s%n", 
                             xmlsr.getLocalName());
                  int nAttrs = xmlsr.getAttributeCount();
                  for (int i = 0; i < nAttrs; i++)
                     out.printf("Attribute [%s,%s]%n",
                                xmlsr.
                                   getAttributeLocalName(i),
                                xmlsr.getAttributeValue(i));
            }
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
      catch (XMLStreamException xmlse)
      {
         xmlse.printStackTrace();
      }
      finally
      {
         if (xmlsr != null)
            try
            {
               xmlsr.close();
            }
            catch (XMLStreamException xmlse)
            {
               err.printf("XMLSE: %s%n", 
                          xmlse.getMessage());
            }
      }
   }
}