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
      "         }," +
      "         {" +
      "            \"category\": \"fiction\"," +
      "            \"author\": \"J. R. R. Tolkien\"," +
      "            \"title\": \"The Lord of the Rings\"," +
      "            \"isbn\": \"0-395-19395-8\"," +
      "            \"price\": 22.99" +
      "         }," +
      "         {" +
      "            \"category\": \"\"," +
      "            \"author\": \"some author\"," +
      "            \"title\": \"some title\"," +
      "            \"isbn\": \"some isbn\"," +
      "            \"price\": 0" +
      "         }" +
      "      ]," +
      "      \"bicycle\":" +
      "      [" +
      "         {" +
      "            \"color\": \"red\"," +
      "            \"accessories\": [\"horn\", " +
      "            \"bottle\"]," +
      "            \"price\": 619.95" +
      "         }," +
      "         {" +
      "            \"color\": \"green\"," +
      "            \"accessories\": [\"horn\", " +
      "            \"light\"]," +
      "            \"price\": 639.95" +
      "         }," +
      "         {" +
      "            \"color\": \"blue\"," +
      "            \"accessories\": []," +
      "            \"price\": 599.95" +
      "         }" +
      "      ]" +
      "   }" +
      "}";

      String expr = "$.store.book[?(@.isbn)].title";
      List<Object> titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = 
         "$.store.book[?(@.category == 'fiction')].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = "$..book[?(@.author =~ /.*REES/i)].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = 
         "$..book[?(@.price > 10 && @.price < 20)].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = "$..book[?(@.author in ['Nigel Rees'])].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = 
         "$..book[?(@.author nin ['Nigel Rees'])].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = "$.store.bicycle[?(@.accessories " +
             "subsetof ['horn', 'bottle', 'light'])].price";
      List<Object> prices = JsonPath.read(json, expr);
      out.println(prices);
      expr = "$.store.bicycle[?(@.accessories " +
             "subsetof ['horn', 'bottle'])].price";
      prices = JsonPath.read(json, expr);
      out.println(prices);
      expr = "$..book[?(@.author size 12)].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = "$..book[?(@.author size 13)].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = 
         "$..bicycle[?(@.accessories empty true)].price";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = 
         "$..bicycle[?(@.accessories empty false)].price";
      titles = JsonPath.read(json, expr);
      out.println(titles);
      expr = "$..book[?(@.category empty true)].title";
      titles = JsonPath.read(json, expr);
      out.println(titles);
   }
}