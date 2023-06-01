package de;

import de.entity.Book;
import de.service.AuthorService;
import de.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class MainClass  implements CommandLineRunner {
  @Autowired final AuthorService authorService ;
@Autowired final BookService bookService ;

    public MainClass(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class , args) ;
    }


    @Override
    public void run(String... args) throws Exception {
        Boolean flag = true ;
        while (flag)
        {
            System.out.println("1.Added Author ");
            System.out.println("2.Author delete");
            System.out.println("3.Book added");
            System.out.println("4.Book delete");
            System.out.println("5.Author Informations");
            System.out.println("6.Author profile based on the book");
            System.out.println("7.Exit");
            int number = 0 ;
            System.out.println("Choose a number");
            try {
                number = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.err.println("number is undefined");
                continue;
            }
            switch (number)
             {
                case  1 :
                    System.out.println("First_name  : " );
                    String first_name = new Scanner(System.in).next() ;
                    System.out.println("Last_name  : " );
                    String last_name = new Scanner(System.in).next() ;
                    System.out.println("Age : ");
                    String strtingofage = new Scanner(System.in).next();
                    int age = 0;
                    while (true)
                    {
                        try {
                            age = Integer.parseInt(strtingofage);
                            if (age > 0 )
                                break;

                        } catch (NumberFormatException e) {
                            System.out.println(" Input is False ");
                        }
                    }

                    authorService.register(first_name , last_name , age) ;
                    break ;
                case  2 :
                    first_name = new Scanner(System.in).next() ;
                    last_name = new Scanner(System.in).next() ;
                    authorService.delete(first_name , last_name );
                    break;
                case 3 :
                    System.out.println("Name_Of_Author   : " );
                     first_name = new Scanner(System.in).next() ;
                    System.out.println("Last_name_Of_Author  : " );
                     last_name = new Scanner(System.in).next() ;
                    System.out.println("Age : ");
                     strtingofage = new Scanner(System.in).next();
                     age = 0;
                    while (true)
                    {
                        try {
                            age = Integer.parseInt(strtingofage);
                            if (age > 0 )
                                break;

                        } catch (NumberFormatException e) {
                            System.out.println(" Input is False ");
                        }
                    }
                    int authorId = authorService.idofAuthor(first_name, last_name , age );
                    System.out.println("Title_of_Book   : " );
                    String title = new Scanner(System.in).next() ;
                    System.out.println("yearOfPublication   : " );
                    int yearOfPublication = new Scanner(System.in).nextInt();
                    System.out.println("monatOfPublication   : " );
                    int monatOfPublication = new Scanner(System.in).nextInt();
                    LocalDate localDate = LocalDate.ofYearDay(yearOfPublication, monatOfPublication);
                    bookService.addBook(authorId , title , localDate );
                    break;
                case 4 :
                    System.out.println("The name of the book to be deleted");
                     title = new Scanner(System.in).next() ;
                    bookService.deletebook(title);
                    break;
                 case  5 :
                     System.out.println("Information about the author that is searched");
                     System.out.println("Name_Of_Author   : " );
                     first_name = new Scanner(System.in).next() ;
                     System.out.println("Last_name_Of_Author  : " );
                     last_name = new Scanner(System.in).next() ;
                     authorService.authorinformation(first_name , last_name );
                     break;
                 case  6 :
                     System.out.println("The information of the book that is searched");
                     System.out.println("Title_of_Book   : " );
                      title = new Scanner(System.in).next() ;
                      bookService.bookinformation(title);
                     break;
                case  7 :
                    flag = false ;
                    break ;
            }

        }




    }
}
