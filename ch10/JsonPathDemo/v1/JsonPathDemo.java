import java.util.HashMap;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import static java.lang.System.*;

public class JsonPathDemo
{
   public static void main(String[] args)
   {
      String json =
      "{" +
      "   \"store\":" +
      "   {" +
      "      \"book\":" +
      "      [" +
      "         {" +
      "            \"category\": \"reference\"," +
      "            \"author\": \"Nigel Rees\"," +
      "            \"title\": \"Sayings of the Century\"," +
      "            \"price\": 8.95" +
      "         }," +
      "         {" +
      "            \"category\": \"fiction\"," +
      "            \"author\": \"Evelyn Waugh\"," +
      "            \"title\": \"Sword of Honour\"," +
      "            \"price\": 12.99" +
      "         }" +
      "      ]," +
      "      \"bicycle\":" +
      "      {" +
      "         \"color\": \"red\"," +
      "         \"price\": 19.95" +
      "      }" +
      "   }" +
      "}";

      JsonPath path = JsonPath.compile("$.store.book[1]");
      HashMap books = path.read(json);
      out.println(books);
      List<Object> authors = 
         JsonPath.read(json, "$.store.book[*].author");
      out.println(authors);
      String author = 
         JsonPath.read(json, "$.store.book[1].author");
      out.println(author);
   }
}