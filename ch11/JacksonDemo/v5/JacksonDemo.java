import java.io.File;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.createObjectNode();
      ObjectNode objectNode = (ObjectNode) rootNode;
      objectNode.put("firstName", "John");
      objectNode.put("lastName", "Doe");
      objectNode.put("age", 42);
      ObjectNode addressNode = mapper.createObjectNode();
      addressNode.put("street", "400 Some Street");
      addressNode.put("city", "Beverly Hills");
      addressNode.put("state", "CA");
      addressNode.put("zipcode", 90210);
      objectNode.set("address", addressNode);
      ArrayNode phoneNumbersNode = mapper.createArrayNode();
      ObjectNode phoneNumberNode = 
         mapper.createObjectNode();
      phoneNumberNode.put("type", "home");
      phoneNumberNode.put("number", "310 555-1234");
      phoneNumbersNode.add(phoneNumberNode);
      phoneNumberNode = mapper.createObjectNode();
      phoneNumberNode.put("type", "fax");
      phoneNumberNode.put("number", "310 555-4567");
      phoneNumbersNode.add(phoneNumberNode);
      objectNode.set("phoneNumbers", phoneNumbersNode);
      JsonFactory factory = new JsonFactory();
      JsonGenerator generator = 
         factory.createGenerator(new File("person.json"), 
                                 JsonEncoding.UTF8);
      generator.useDefaultPrettyPrinter();
      mapper.writeTree(generator, rootNode);      
      out.println("person.json successfully generated");
   }
}