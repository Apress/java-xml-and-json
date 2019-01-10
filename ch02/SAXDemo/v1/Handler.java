import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

import org.xml.sax.ext.DefaultHandler2;

import static java.lang.System.*;

public class Handler extends DefaultHandler2
{
   private Locator locator;

   @Override
   public void characters(char[] ch, int start, int length)
   {
      out.print("characters() [");
      for (int i = start; i < start + length; i++)
         out.print(ch[i]);
      out.println("]");
   }

   @Override
   public void comment(char[] ch, int start, int length)
   {
      out.print("characters() [");
      for (int i = start; i < start + length; i++)
         out.print(ch[i]);
      out.println("]");
   }

   @Override
   public void endCDATA()
   {
      out.println("endCDATA()");
   }

   @Override
   public void endDocument()
   {
      out.println("endDocument()");
   }

   @Override
   public void endDTD()
   {
      out.println("endDTD()");
   }

   @Override
   public void endElement(String uri, String localName, 
                          String qName)
   {
      out.print("endElement() ");
      out.printf("uri=[%s], ", uri);
      out.printf("localName=[%s], ", localName);
      out.printf("qName=[%s]%n", qName);
   }

   @Override
   public void endEntity(String name)
   {
      out.print("endEntity() ");
      out.printf("name=[%s]%n", name);
   }

   @Override
   public void endPrefixMapping(String prefix)
   {
      out.print("endPrefixMapping() ");
      out.printf("prefix=[%s]%n", prefix);
   }

   @Override
   public void error(SAXParseException saxpe)
   {
      out.printf("error() %s%n", saxpe.toString());
   }

   @Override
   public void fatalError(SAXParseException saxpe)
   {
      out.printf("fatalError() %s%n", saxpe.toString());
   }

   @Override
   public void ignorableWhitespace(char[] ch, int start, 
                                   int length)
   {
      out.print("ignorableWhitespace() [");
      for (int i = start; i < start + length; i++)
         out.print(ch[i]);
      out.println("]");
   }

   @Override
   public void notationDecl(String name, String publicId,
                            String systemId)
   {
      out.print("notationDecl() ");
      out.printf("name=[%s], ", name);
      out.printf("publicId=[%s], ", publicId);
      out.printf("systemId=[%s]%n", systemId);
   }

   @Override
   public void processingInstruction(String target, 
                                     String data)
   {
      out.print("processingInstruction() ");
      out.printf("target=[%s], ", target);
      out.printf("data=[%s]%n", data);
   }

   @Override
   public InputSource resolveEntity(String publicId, 
                                    String systemId)
   {
      out.print("resolveEntity() ");
      out.printf("publicId=[%s], ", publicId);
      out.printf("systemId=[%s]%n", systemId);
      // Do not perform a remapping.
      InputSource is = new InputSource();
      is.setPublicId(publicId);
      is.setSystemId(systemId);
      return is;
   }

   @Override
   public void setDocumentLocator(Locator locator)
   {
      out.print("setDocumentLocator() ");
      out.printf("locator=[%s]%n", locator);
      this.locator = locator;
   }

   @Override
   public void skippedEntity(String name)
   {
      out.print("skippedEntity() ");
      out.printf("name=[%s]%n", name);
   }

   @Override
   public void startCDATA()
   {
      out.println("startCDATA()");
   }

   @Override
   public void startDocument()
   {
      out.println("startDocument()");
   }

   @Override
   public void startDTD(String name, String publicId, 
                        String systemId)
   {
      out.print("startDTD() ");
      out.printf("name=[%s], ", name);
      out.printf("publicId=[%s], ", publicId);
      out.printf("systemId=[%s]%n", systemId);
   }

   @Override
   public void startElement(String uri, String localName, 
                            String qName,
                            Attributes attributes)
   {
      out.print("startElement() ");
      out.printf("uri=[%s], ", uri);
      out.printf("localName=[%s], ", localName);
      out.printf("qName=[%s]%n", qName);
      for (int i = 0; i < attributes.getLength(); i++)
         out.printf("  Attribute: %s, %s%n", 
                            attributes.getLocalName(i), 
                            attributes.getValue(i));
      out.printf("Column number=[%d]%n",
                         locator.getColumnNumber());
      out.printf("Line number=[%d]%n",
                         locator.getLineNumber());
   }

   @Override
   public void startEntity(String name)
   {
      out.print("startEntity() ");
      out.printf("name=[%s]%n", name);
   }

   @Override
   public void startPrefixMapping(String prefix, 
                                  String uri)
   {
      out.print("startPrefixMapping() ");
      out.printf("prefix=[%s], ", prefix);
      out.printf("uri=[%s]%n", uri);
   }

   @Override
   public void unparsedEntityDecl(String name, 
                                  String publicId,
                                  String systemId, 
                                  String notationName)
   {
      out.print("unparsedEntityDecl() ");
      out.printf("name=[%s], ", name);
      out.printf("publicId=[%s], ", publicId);
      out.printf("systemId=[%s], ", systemId);
      out.printf("notationName=[%s]%n", notationName);
   }

   @Override
   public void warning(SAXParseException saxpe)
   {
      out.printf("warning() %s%n", saxpe.toString());
   }
}