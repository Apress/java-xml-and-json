import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.
       JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.*;

public class JacksonDemo
{
   public static void main(String[] args) throws Exception
   {
      ObjectMapper mapper = new ObjectMapper();
      SavingsAccount1 sa1 = 
         new SavingsAccount1("101", "John Doe", 50000);
      mapper.writeValue(new File("sa1.json"), sa1);
      SavingsAccount2 sa2 = 
         new SavingsAccount2("101", "John Doe", 50000);
      mapper.writeValue(new File("sa2.json"), sa2);
      SavingsAccount3 sa3 = 
         new SavingsAccount3("101", "John Doe", 50000);
      mapper.writeValue(new File("sa3.json"), sa3);
      sa1 = mapper.readValue(new File("sa1.json"), 
                             SavingsAccount1.class);
      out.printf("bankID = %s%n", sa1.bankID);
      out.printf("accountOwnerName = %s%n", 
                 sa1.accountOwnerName);
      out.printf("balanceInCents = %d%n", 
                 sa1.balanceInCents);
      sa2 = mapper.readValue(new File("sa1.json"), 
                             SavingsAccount2.class);
      out.printf("bankID = %s%n", sa2.bankID);
      out.printf("accountOwnerName = %s%n", 
                 sa2.accountOwnerName);
      out.printf("balanceInCents = %d%n", 
                 sa2.balanceInCents);
      sa3 = mapper.readValue(new File("sa1.json"), 
                             SavingsAccount3.class);
      out.printf("bankID = %s%n", sa3.bankID);
      out.printf("accountOwnerName = %s%n", 
                 sa3.accountOwnerName);
      out.printf("balanceInCents = %d%n", 
                 sa3.balanceInCents);
   }
}

class SavingsAccount1
{
   public String bankID;
   public String accountOwnerName;
   public long balanceInCents;

   SavingsAccount1()
   {
   }

   SavingsAccount1(String bankID, String accountOwnerName, 
                   long balanceInCents)
   {
      this.bankID = bankID;
      this.accountOwnerName = accountOwnerName;
      this.balanceInCents = balanceInCents;
   }
}

class SavingsAccount2
{
   @JsonIgnore
   public String bankID;
   public String accountOwnerName;
   public long balanceInCents;

   SavingsAccount2()
   {
   }

   SavingsAccount2(String bankID, String accountOwnerName, 
                   long balanceInCents)
   {
      this.bankID = bankID;
      this.accountOwnerName = accountOwnerName;
      this.balanceInCents = balanceInCents;
   }
}

@JsonIgnoreProperties({"bankID", "accountOwnerName"})
class SavingsAccount3
{
   public String bankID;
   public String accountOwnerName;
   public long balanceInCents;

   SavingsAccount3()
   {
   }

   SavingsAccount3(String bankID, String accountOwnerName, 
                   long balanceInCents)
   {
      this.bankID = bankID;
      this.accountOwnerName = accountOwnerName;
      this.balanceInCents = balanceInCents;
   }
}