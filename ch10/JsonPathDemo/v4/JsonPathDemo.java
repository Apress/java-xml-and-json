import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

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
      "         }" +
      "      ]," +
      "      \"bicycle\":" +
      "      {" +
      "         \"color\": \"red\"," +
      "         \"price\": 19.95" +
      "      }" +
      "   }" +
      "}";
      
      Predicate expensiveBooks = 
         new Predicate() 
         {
            @Override
            public boolean apply(PredicateContext ctx) 
            {
               String value = ctx.item(Map.class).
                              get("price").toString();
               return Float.valueOf(value) > 20.00;
            }
         };
      String expr = "$.store.book[?]";
      List<Map<String, Object>> titles = 
         JsonPath.read(json, expr, expensiveBooks);
      out.println(titles);
   }
}