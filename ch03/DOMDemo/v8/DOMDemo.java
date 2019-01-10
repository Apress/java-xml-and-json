import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;

import static java.lang.System.*;

public class DOMDemo
{
   public static void main(String[] args) throws Exception
   {
      DocumentBuilderFactory dbf = 
         DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      DOMImplementation di = db.getDOMImplementation();
      if (!di.hasFeature("Range", "2.0"))
      {
         err.println("parser doesn't support range");
         return;
      }
      Document doc = db.parse("recipe.xml");
      Range r = ((DocumentRange) doc).createRange();
      Element root = doc.getDocumentElement();
      r.selectNodeContents(root.getFirstChild().
                                getNextSibling().
                                getNextSibling().
                                getNextSibling());
      r.deleteContents();
      DOMImplementationLS ls = (DOMImplementationLS) 
         DOMImplementationRegistry.newInstance().
         getDOMImplementation("LS");
      LSSerializer serializer = ls.createLSSerializer();
      if (serializer.writeToURI(doc, "_recipe.xml"))
         out.println("serialization successful");
   }
}