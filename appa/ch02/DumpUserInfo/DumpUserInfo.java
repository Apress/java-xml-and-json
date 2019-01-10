import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.xml.sax.helpers.DefaultHandler;

import static java.lang.System.*;

public class DumpUserInfo
{
   public static void main(String[] args)
   {
      try
      {
         SAXParserFactory spf = 
            SAXParserFactory.newInstance();
         spf.setNamespaceAware(true);
         SAXParser sp = spf.newSAXParser();
         XMLReader xmlr = sp.getXMLReader();
         Handler handler = new Handler();
         xmlr.setContentHandler(handler);
         FileReader fr = new FileReader("tomcat-users.xml");
         xmlr.parse(new InputSource(fr));
      }
      catch (IOException ioe)
      {
         err.printf("IOE: %s%n", ioe.toString());
      }
      catch (ParserConfigurationException pce)
      {
         err.printf("PCE: %s%n", pce.toString());
      }
      catch (SAXException saxe)
      {
         err.printf("SAXE: %s%n", saxe.toString());
      }
   }
}

class Handler extends DefaultHandler
{
   @Override
   public void startElement(String uri, String localName, 
                            String qName, 
                            Attributes attributes)
   {
      if (localName.equals("user"))
      {
         for (int i = 0; i < attributes.getLength(); i++)
            out.printf("%s = %s%n", 
                       attributes.getLocalName(i),
                       attributes.getValue(i));
         out.println();
      }
   }
}