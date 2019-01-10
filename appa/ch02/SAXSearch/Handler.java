import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;

import org.xml.sax.ext.DefaultHandler2;

import static java.lang.System.*;

public class Handler extends DefaultHandler2
{
   private boolean isPublisher, isTitle;

   private String isbn, publisher, pubYear, title, srchText;

   public Handler(String srchText)
   {
      this.srchText = srchText;
   }

   @Override
   public void characters(char[] ch, int start, int length)
   {
      if (isTitle)
      {
         title = new String(ch, start, length).trim();
         isTitle = false;
      }
      else
      if (isPublisher)
      {
         publisher = new String(ch, start, length).trim();
         isPublisher = false;
      }
   }

   @Override
   public void endElement(String uri, String localName, 
                          String qName)
   {
      if (!localName.equals("book"))
         return;
      if (!srchText.equals(publisher))
         return;
      out.printf("title = %s, isbn = %s%n", title, isbn);
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
   public void startElement(String uri, String localName, 
                            String qName, 
                            Attributes attributes)
   {
      if (localName.equals("title"))
      {
         isTitle = true;
         return;
      }
      else
      if (localName.equals("publisher"))
      {
         isPublisher = true;
         return;
      }
      if (!localName.equals("book"))
         return;
      for (int i = 0; i < attributes.getLength(); i++)
         if (attributes.getLocalName(i).equals("isbn"))
            isbn = attributes.getValue(i);
         else
         if (attributes.getLocalName(i).equals("pubyear"))
            pubYear = attributes.getValue(i);
   }

   @Override
   public void warning(SAXParseException saxpe)
   {
      out.printf("warning() %s%n", saxpe.toString());
   }
}