import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import org.xml.sax.SAXException;

import static java.lang.System.*;

public class DOMDemo
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java DOMDemo xmlfile");
         return;
      }
      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         DocumentBuilder db = dbf.newDocumentBuilder();
         DOMImplementation di = db.getDOMImplementation();
         if (!di.hasFeature("Traversal", "2.0"))
         {
            err.println("parser doesn't support " +
                               "traversal");
            return;
         }
         Document doc = db.parse(args[0]);
         out.printf("Version = %s%n", 
                           doc.getXmlVersion());
         out.printf("Encoding = %s%n", 
                           doc.getXmlEncoding());
         out.printf("Standalone = %b%n%n", 
                           doc.getXmlStandalone());
         NodeIterator ni = 
            ((DocumentTraversal) doc).
            createNodeIterator(doc.getDocumentElement(),
                               NodeFilter.SHOW_ELEMENT,
                               null, true);         
         Node node = ni.nextNode();
         while (node != null)
         {
            dump((Element) node);
            node = ni.nextNode();
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
   }
}