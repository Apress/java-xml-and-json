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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import static java.lang.System.*;

public class XSLTDemo
{
   final static String KEY_INDENT =
      "{http://xml.apache.org/xslt}indent-amount";

   public static void main(String[] args)
   {
      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.newDocument();
         doc.setXmlStandalone(true);
         // Create the root element.
         Element root = doc.createElement("movie");
         doc.appendChild(root);
         // Create name child element and add it to the 
         // root.
         Element name = doc.createElement("name");
         root.appendChild(name);
         // Add a text element to the name element.
         Text text = doc.createTextNode("Le Fabuleux " +
                                        "Destin d'Amélie " +
                                        "Poulain");
         name.appendChild(text);
         // Create language child element and add it to the 
         // root.
         Element language = doc.createElement("language");
         root.appendChild(language);
         // Add a text element to the language element.
         text = doc.createTextNode("français");
         language.appendChild(text);
         // Use a transformer to output this tree with 
         // ISO-8859-1 encoding to the standard output 
         // stream.
         TransformerFactory tf = 
            TransformerFactory.newInstance();
         Transformer t = tf.newTransformer();
         t.setOutputProperty(OutputKeys.METHOD, "xml");
         t.setOutputProperty(OutputKeys.ENCODING, 
                             "ISO-8859-1");
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         t.setOutputProperty(KEY_INDENT, "3");
         Source source = new DOMSource(doc);
         Result result = new StreamResult(out);
         t.transform(source, result);
      }
      catch (FactoryConfigurationError fce)
      {
         err.printf("FCE: %s%n", fce.toString());
      }
      catch (ParserConfigurationException pce)
      {
         err.printf("PCE: %s%n", pce.toString());
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