import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.
       TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.
       TransformerFactoryConfigurationError;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import static java.lang.System.*;

public class MakeHTML
{
   public static void main(String[] args)
   {
      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.parse("books.xml");
         TransformerFactory tf = 
            TransformerFactory.newInstance();
         StreamSource ssStyleSheet;
         FileReader fr = new FileReader("books.xsl");
         ssStyleSheet = new StreamSource(fr);
         Transformer t = tf.newTransformer(ssStyleSheet);
         t.setOutputProperty(OutputKeys.METHOD, "html");
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         Source source = new DOMSource(doc);
         Result result = new StreamResult(out);
         t.transform(source, result);
      }
      catch (IOException ioe)
      {
         err.printf("IOE: %s%n", ioe.toString());
      }
      catch (FactoryConfigurationError fce)
      {
         err.printf("FCE: %s%n", fce.toString());
      }
      catch (ParserConfigurationException pce)
      {
         err.printf("PCE: %s%n", pce.toString());
      }
      catch (SAXException saxe)
      {
         err.printf("SAXE: %s%n", saxe.toString());
      }
      catch (TransformerConfigurationException tce)
      {
         err.printf("TCE: %s%n", tce.toString());
      }
      catch (TransformerException te)
      {
         err.printf("TE: %s%n", te.toString());
      }
      catch (TransformerFactoryConfigurationError tfce)
      {
         err.printf("TFCE: %s%n", tfce.toString());
      }
   }
}