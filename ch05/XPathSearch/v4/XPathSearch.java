import java.io.IOException;

import javax.xml.namespace.QName;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathVariableResolver;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import static java.lang.System.*;

public class XPathSearch
{
   public static void main(String[] args)
   {
      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.parse("example.xml");
         XPathFactory xpf = XPathFactory.newInstance();
         XPath xp = xpf.newXPath();
         XPathVariableResolver xpvr =
            new XPathVariableResolver()
            {
               @Override
               public Object resolveVariable(QName varname)
               {
                  if (varname.getLocalPart().equals("d"))
                     return "x";
                  else
                     return null;
               }
            };
         xp.setXPathVariableResolver(xpvr);
         XPathExpression xpe;
         xpe = xp.compile("/a/b[@c = $d]/text()");
         Object result = 
            xpe.evaluate(doc, XPathConstants.NODESET);
         NodeList nl = (NodeList) result;
         for (int i = 0; i < nl.getLength(); i++)
            out.println(nl.item(i).getNodeValue()); 
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
      catch (XPathException xpe)
      {
         err.printf("XPE: %s%n", xpe.toString());
      }
   }
}