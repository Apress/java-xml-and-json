import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import static java.lang.System.*;

public class StAXDemo
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java StAXDemo xmlfile");
         return;
      }
      try
      {
         XMLInputFactory xmlif = 
            XMLInputFactory.newFactory();
         FileReader fr = new FileReader(args[0]);
         XMLEventReader xmler =
            xmlif.createXMLEventReader(fr);
         while (xmler.hasNext())
         {
            XMLEvent xmle = xmler.nextEvent();
            switch (xmle.getEventType())
            {
               case XMLEvent.START_ELEMENT:
                  out.println("START_ELEMENT");
                  out.printf("  Qname = %s%n", 
                             ((StartElement) xmle).
                             getName());
                  break;
               case XMLEvent.END_ELEMENT:
                  out.println("END_ELEMENT");
                  out.printf("  Qname = %s%n",
                             ((EndElement) xmle).
                             getName());
            }
         }
      }
      catch (FactoryConfigurationError fce)
      {
         err.printf("FCE: %s%n", fce.toString());
      }
      catch (FileNotFoundException fnfe)
      {
         err.printf("FNFE: %s%n", fnfe.toString());
      }
      catch (XMLStreamException xmlse)
      {
         err.printf("XMLSE: %s%n", xmlse.toString());
      }
   }
}