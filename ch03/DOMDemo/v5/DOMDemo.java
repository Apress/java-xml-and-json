import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSParserFilter;

import org.w3c.dom.traversal.NodeFilter;

import static java.lang.System.*;

class InputFilter implements LSParserFilter
{
   private boolean accept;

   InputFilter(boolean accept)
   {
      this.accept = accept;
   }

   @Override
   public short acceptNode(Node node)
   {
      return (accept) ? FILTER_ACCEPT : FILTER_REJECT;
   }

   @Override
   public int getWhatToShow()
   {
      return NodeFilter.SHOW_ELEMENT;
   }

   @Override
   public short startElement(Element e)
   {
      return LSParserFilter.FILTER_ACCEPT;
   }
}

public class DOMDemo
{
   public static void main(String[] args) throws Exception
   {
      if (args.length != 2)
      {
         err.println("usage: java DOMDemo xmlfile " +
                     "accept|reject");
         return;
      }
      DOMImplementationLS ls = (DOMImplementationLS) 
         DOMImplementationRegistry.newInstance().
         getDOMImplementation("LS");
      LSParser parser = 
         ls.createLSParser(DOMImplementationLS.
                           MODE_SYNCHRONOUS, null);
      LSParserFilter filter = 
         new InputFilter(args[1].equals("accept"));
      parser.setFilter(filter);
      Document doc = parser.parseURI(args[0]);
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