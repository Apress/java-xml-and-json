import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      Person1 person1 = new Person1();
      person1.name = "John Doe";
      person1.address = new Person1.Address();
      person1.address.street = "100 Smith Street";
      person1.address.city = "SomeCity";
      mapper.writeValue(new File("person1.json"), person1);
      Person2 person2 = new Person2();
      person2.name = "John Doe";
      person2.address = new Person2.Address();
      person2.address.street = "100 Smith Street";
      person2.address.city = "SomeCity";
      mapper.writeValue(new File("person2.json"), person2);
      person1 = mapper.readValue(new File("person1.json"),
                                 Person1.class);
      out.printf("name = %s%n", person1.name);
      out.printf("street = %s%n", person1.address.street);
      out.printf("city = %s%n", person1.address.city);
      person2 = mapper.readValue(new File("person1.json"),
                                 Person2.class);
      out.printf("name = %s%n", person2.name);
      out.printf("street = %s%n", person2.address.street);
      out.printf("city = %s%n", person2.address.city);
   }
}

class Person1
{
   public String name;

   public static class Address
   {
      public String street;
      public String city;
   }

   public Address address;
}

class Person2
{
   public String name;

   @JsonIgnoreType
   public static class Address
   {
      public String street;
      public String city;
   }

   public Address address;
}