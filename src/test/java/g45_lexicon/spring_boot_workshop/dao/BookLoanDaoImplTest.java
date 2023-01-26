package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Book;
import g45_lexicon.spring_boot_workshop.entity.BookLoan;
import g45_lexicon.spring_boot_workshop.entity.Details;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
public class BookLoanDaoImplTest {
    @Autowired
    TestEntityManager tem;
    @Autowired
    BookLoanDaoImpl testBookLoan;

    int loanId1;
    int loanId2;
    int bookId1;
    int bookId2;
    int bookId3;
    int bookId4;

    int detailsDataId1;
    int detailsDataId2;
    int detailsDataId3;
    int appUserId1;
    int appUserId2;
    int appUserId3;

    @BeforeEach
    public void setup() {
        //create users in db
        Details detailsData1 = new Details("email1","Full Name1", LocalDate.parse("2023-01-01"));
        AppUser appUserData1 = new AppUser("user1","pass1",detailsData1);
        AppUser appUser1 = tem.persist(appUserData1);
        Details detailsData2 = new Details("email2","Full Name2", LocalDate.parse("2023-01-02"));
        AppUser appUserData2 = new AppUser("user2","pass2",detailsData2);
        AppUser appUser2 = tem.persist(appUserData2);
        Details detailsData3 = new Details("email3","Full Name3", LocalDate.parse("2023-01-03"));
        AppUser appUserData3 = new AppUser("user3","pass3",detailsData3);
        AppUser appUser3 = tem.persist(appUserData3);
        appUserId1 = appUser1.getAppUserId();
        appUserId2 = appUser2.getAppUserId();
        appUserId3 = appUser3.getAppUserId();
        detailsDataId1 = detailsData1.getDetailsId();
        detailsDataId2 = detailsData2.getDetailsId();
        detailsDataId3 = detailsData3.getDetailsId();

        //create books in db
        Book bookData1 = new Book("978-1-4747-7314-0","book1",7);
        Book book1 = tem.persist(bookData1);
        Book bookData2 = new Book("978-7-5621-7275-8","book2",14);
        Book book2 = tem.persist(bookData2);
        Book bookData3 = new Book("978-7-9416-1704-7","book3",21);
        Book book3 = tem.persist(bookData1);
        Book bookData4 = new Book("978-4-9852-7910-1","book4",30);
        Book book4 = tem.persist(bookData1);
        bookId1 = book1.getBookId();
        bookId2 = book2.getBookId();
        bookId3 = book3.getBookId();
        bookId4 = book4.getBookId();

        //create bookLoan in db
            //create borrowersList
            List<AppUser> borrowersList1 = new ArrayList<>();
            borrowersList1.add(appUser1);
            List<AppUser> borrowersList2 = new ArrayList<>();
            borrowersList2.add(appUser3);
            borrowersList2.add(appUser2);
            List<AppUser> borrowersList3 = new ArrayList<>();
            borrowersList3.add(appUser1);
            borrowersList3.add(appUser2);
            borrowersList3.add(appUser3);

            //create booksList
        List<Book> booksList1 = new ArrayList<>();
        booksList1.add(book1);
        booksList1.add(book3);

        List<Book> booksList2 = new ArrayList<>();
        booksList2.add(book1);
        booksList2.add(book3);
        booksList2.add(book4);

        List<Book> booksList3 = new ArrayList<>();
        booksList3.add(book1);
        booksList3.add(book2);
        booksList3.add(book3);
        booksList3.add(book4);

        //create bookLoan objects
        BookLoan bookLoanData1 = new BookLoan(LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-07"), true, borrowersList1, booksList2);
        BookLoan bookLoanData2 = new BookLoan(LocalDate.parse("2023-01-02"), LocalDate.parse("2023-01-15"), true, borrowersList3, booksList1);
        BookLoan bookLoan1 = tem.persist(bookLoanData1);
        BookLoan bookLoan2 = tem.persist(bookLoanData2);
        loanId1 = bookLoan1.getLoanId();
        loanId2 = bookLoan2.getLoanId();
    }

    @Test
    public void findById() {
        BookLoan foundBookLoan = testBookLoan.findById(bookId4);
        assertNotNull(foundBookLoan);
        System.out.println(foundBookLoan);
    }

    @Test
    public void findAll() {
        Collection<BookLoan> allBookLoans = testBookLoan.findAll();
        System.out.println(allBookLoans);
    }

    @Test
    public void create() {
        //create users data
        Details detailsData5 = new Details("email5","Full Name5", LocalDate.parse("2023-01-05"));
        AppUser appUserData5 = new AppUser("user5","pass5",detailsData5);
        AppUser appUser5 = tem.persist(appUserData5);
        Details detailsData6 = new Details("email6","Full Name6", LocalDate.parse("2023-01-06"));
        AppUser appUserData6 = new AppUser("user6","pass6",detailsData6);
        AppUser appUser6 = tem.persist(appUserData6);

        //create books data
        Book bookData5 = new Book("978-9-6747-0708-8","book5",60);
        Book book5 = tem.persist(bookData5);
        Book bookData6 = new Book("978-0-8090-0223-8","book6",90);
        Book book6 = tem.persist(bookData6);
        List<AppUser> borrowersList = new ArrayList<>();
        borrowersList.add(appUser5);
        borrowersList.add(appUser6);

        //create usersList and booksList to add to bookLoan data
        List<Book> booksList = new ArrayList<>();
        booksList.add(book5);
        booksList.add(book6);

        //create new bookLoan object to add to db
        BookLoan bookLoanData = new BookLoan(LocalDate.parse("2023-01-15"), LocalDate.parse("2023-01-25"), true, borrowersList, booksList);
        BookLoan bookLoan = tem.persist(bookLoanData);

        assertNotNull(bookLoan);
        System.out.println(testBookLoan.findAll());
    }

    @Test
    public void update() {
       /* BookLoan sourceBookLoan = tem.find(BookLoan.class, bookLoan.getLoanId());
        sourceBookLoan.setLoanDate(LocalDate.parse("2022-12-30"));
        testBookLoan.update()*/
    }

    @Test
    public void delete() throws DataNotFoundException {
        BookLoan foundBookLoan = testBookLoan.findById(appUserId1);
        testBookLoan.delete(foundBookLoan.getLoanId());
        System.out.println(testBookLoan.findAll());
    }
}
