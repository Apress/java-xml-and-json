import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
         XMLStreamReader xmlsr = 
            xmlif.createXMLStreamReader(fr);
         while (xmlsr.hasNext())
         {
            switch (xmlsr.next())
            {
               case XMLStreamReader.START_ELEMENT:
                  out.println("START_ELEMENT");
                  out.printf("  Qname = %s%n", 
                             xmlsr.getName());
                  break;
               case XMLStreamReader.END_ELEMENT:
                  out.println("END_ELEMENT");
                  out.printf("  Qname = %s%n",
                             xmlsr.getName());
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