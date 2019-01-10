import org.w3c.dom.Document;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import static java.lang.System.*;

public class DOMDemo
{
   public static void main(String[] args) throws Exception
   {
      if (args.length != 1)
      {
         err.println("usage: java DOMDemo xmlfile");
         return;
      }
      DOMImplementationLS ls = (DOMImplementationLS) 
         DOMImplementationRegistry.newInstance().
         getDOMImplementation("LS");
      LSParser parser = 
         ls.createLSParser(DOMImplementationLS.
                           MODE_SYNCHRONOUS, null);
      Document doc = parser.parseURI(args[0]);
      LSSerializer serializer = ls.createLSSerializer();
      if (serializer.writeToURI(doc, "_" + args[0]))
         out.println("serialization successful");
   }
}