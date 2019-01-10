import com.google.gson.Gson;

import com.google.gson.annotations.SerializedName;

import static java.lang.System.*;

public class GsonDemo
{
   static class Book
   {
      String title;
      @SerializedName("isbn-10")
      String isbn10;
      @SerializedName("isbn-13")
      String isbn13;
   }

   public static void main(String[] args)
   {
      Book book = new Book();
      book.title = "PHP and MySQL Web Development, " +
                   "Second Edition";
      book.isbn10 = "067232525X";
      book.isbn13 = "075-2063325254";
      Gson gson = new Gson();
      String json = gson.toJson(book);
      out.println(json);
      Book book2 = gson.fromJson(json, Book.class);
      out.printf("title = %s%n", book2.title);
      out.printf("isbn10 = %s%n", book2.isbn10);
      out.printf("isbn13 = %s%n", book2.isbn13);
   }
}