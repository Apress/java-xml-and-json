import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import static java.lang.System.*;

public class DOMSearch
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         err.println("usage: java DOMSearch publisher");
         return;
      }

      try
      {
         DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.parse("books.xml");
         class BookItem
         {
            String title;
            String isbn;
         }
         List<BookItem> bookItems = new ArrayList<>();
         NodeList books = doc.getElementsByTagName("book");
         for (int i = 0; i < books.getLength(); i++)
         {
            Element book = (Element) books.item(i);
            NodeList children = book.getChildNodes();
            String title = "";
            for (int j = 0; j < children.getLength(); j++)
            {
               Node child = children.item(j);
               if (child.getNodeType() == Node.ELEMENT_NODE)
               {
                  if (child.getNodeName().equals("title"))
                     title = child.getFirstChild().
                                   getNodeValue().trim();
                  else
                  if (child.getNodeName().
                      equals("publisher"))
                  {
                     // Compare publisher name argument 
                     // (args[0]) with text of publisher's 
                     // child text node. The trim() method 
                     // call removes whitespace that would 
                     // interfere with the comparison.
                     if (args[0].
                         equals(child.getFirstChild().
                                     getNodeValue().trim()))
                     {
                        BookItem bookItem = new BookItem();
                        bookItem.title = title;
                        NamedNodeMap nnm = 
                           book.getAttributes();
                        Node isbn = 
                           nnm.getNamedItem("isbn");
                        bookItem.isbn = isbn.getNodeValue();
                        bookItems.add(bookItem);
                        break;
                     }
                  }
               }
            }
         }
         for (BookItem bookItem: bookItems)
            out.printf("title = %s, isbn = %s%n", 
                       bookItem.title, bookItem.isbn);
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
}