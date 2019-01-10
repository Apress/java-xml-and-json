import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.XMLEvent;

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
         XMLEventWriter xmlew;
         xmlew = xmlof.createXMLEventWriter(fw);
         final XMLEventFactory xmlef = 
            XMLEventFactory.newFactory();
         XMLEvent event = xmlef.createStartDocument();
         xmlew.add(event);
         Iterator<Namespace> nsIter;
         nsIter = new Iterator<Namespace>()
         {
            int index = 0;
            Namespace[] ns;
            {
               ns = new Namespace[2];
               ns[0] = xmlef.createNamespace("h", NS1);
               ns[1] = xmlef.createNamespace("r", NS2);
            }
            @Override
            public boolean hasNext()
            {
               return index != 2;
            }
            @Override
            public Namespace next()
            {
               return ns[index++];
            }
            @Override
            public void remove()
            {
               throw new UnsupportedOperationException();
            }
         };
         event = xmlef.createStartElement("h", NS1, "html", 
                                          null, nsIter);
         xmlew.add(event);
         event = xmlef.createStartElement("h", NS2, "head");
         xmlew.add(event);
         event = xmlef.createStartElement("h", NS1, 
                                          "title");
         xmlew.add(event);
         event = xmlef.createCharacters("Recipe");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "title");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "head");
         xmlew.add(event);
         event = xmlef.createStartElement("h", NS1, "body");
         xmlew.add(event);
         event = xmlef.createStartElement("r", NS2, 
                                          "recipe");
         xmlew.add(event);
         event = xmlef.createStartElement("r", NS2,
                                          "title");
         xmlew.add(event);
         event = xmlef.createCharacters("Grilled Cheese " +
                                        "Sandwich");
         xmlew.add(event);
         event = xmlef.createEndElement("r", NS2,
                                        "title");
         xmlew.add(event);
         event = xmlef.createStartElement("r", NS2,
                                          "ingredients");
         xmlew.add(event);
         event = xmlef.createStartElement("h", NS1, "ul");
         xmlew.add(event);
         event = xmlef.createStartElement("h", NS1, "li");
         xmlew.add(event);
         Iterator<Attribute> attrIter;
         attrIter = new Iterator<Attribute>()
         {
            int index = 0;
            Attribute[] attrs;
            {
               attrs = new Attribute[1];
               attrs[0] = xmlef.createAttribute("qty", "2");
            }
            @Override
            public boolean hasNext()
            {
               return index != 1;
            }
            @Override
            public Attribute next()
            {
               return attrs[index++];
            }
            @Override
            public void remove()
            {
               throw new UnsupportedOperationException();
            }
         };
         event = xmlef.createStartElement("r", NS2, 
                                          "ingredient", 
                                          attrIter, null);
         xmlew.add(event);
         event = xmlef.createCharacters("bread slice");
         xmlew.add(event);
         event = xmlef.createEndElement("r", NS2,
                                        "ingredient");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "li");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "ul");
         xmlew.add(event);
         event = xmlef.createEndElement("r", NS2,
                                        "ingredients");
         xmlew.add(event);
         event = xmlef.createEndElement("r", NS2, "recipe");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "body");
         xmlew.add(event);
         event = xmlef.createEndElement("h", NS1, "html");
         xmlew.add(event);
         xmlew.flush();
         xmlew.close();
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