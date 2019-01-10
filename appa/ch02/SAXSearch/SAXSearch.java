import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import static java.lang.System.*;

public class SAXSearch
{
   final static String PROP_LH =
      "http://xml.org/sax/properties/lexical-handler";

   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java SAXSearch publisher");
         return;
      }

      try
      {
         SAXParserFactory spf = 
            SAXParserFactory.newInstance();
         spf.setNamespaceAware(true);
         SAXParser sp = spf.newSAXParser();
         XMLReader xmlr = sp.getXMLReader();
         Handler handler = new Handler(args[0]);
         xmlr.setContentHandler(handler);
         xmlr.setErrorHandler(handler);
         xmlr.setProperty(PROP_LH, handler);
         FileReader fr = new FileReader("books.xml");
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