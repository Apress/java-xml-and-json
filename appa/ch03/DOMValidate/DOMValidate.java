import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import static java.lang.System.*;

public class DOMValidate
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java DOMValidate xmlfile");
         return;
      }
      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         dbf.setValidating(true);
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.parse(args[0]);
         out.printf("Version = %s%n", doc.getXmlVersion());
         out.printf("Encoding = %s%n", 
                    doc.getXmlEncoding());
         out.printf("Standalone = %b%n%n", 
                    doc.getXmlStandalone());
         if (doc.hasChildNodes())
         {
            NodeList nl = doc.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++)
            {
               Node node = nl.item(i);
               if (node.getNodeType() == Node.ELEMENT_NODE)
                  dump((Element) node);
            }
         }
      }
      catch (IOException ioe)
      {
         err.printf("IOE: %s%n", ioe.toString());
      }
      catch (SAXException saxe)
      {
         err.printf("SAXE: %s%n", saxe.toString());
      }
      catch (FactoryConfigurationError fce)
      {
         err.printf("FCE: %s%n", fce.toString());
      }
      catch (ParserConfigurationException pce)
      {
         err.printf("PCE: %s%n", pce.toString());
      }
   }

   static void dump(Element e)
   {
      out.printf("Element: %s, %s, %s, %s%n", 
                 e.getNodeName(), e.getLocalName(), 
                 e.getPrefix(), e.getNamespaceURI());
      NamedNodeMap nnm = e.getAttributes();
      if (nnm != null)
         for (int i = 0; i < nnm.getLength(); i++)
         {
            Node node = nnm.item(i);
            Attr attr = 
               e.getAttributeNode(node.getNodeName());
            out.printf("  Attribute %s = %s%n", 
                       attr.getName(), attr.getValue());
         }
      NodeList nl = e.getChildNodes();
      for (int i = 0; i < nl.getLength(); i++)
      {
         Node node = nl.item(i);
         if (node instanceof Element)
            dump((Element) node);
      }
   }
}