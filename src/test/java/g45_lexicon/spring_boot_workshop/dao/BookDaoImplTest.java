package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Book;
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

import java.time.LocalDate;
import java.util.Collection;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
public class BookDaoImplTest {
    @Autowired
    TestEntityManager tem;
    @Autowired
    BookDaoImpl testBook;

    int bookId1;
    int bookId2;

    @BeforeEach
    public void setup() {
        Book bookData1 = new Book("978-1-4747-7314-0","book1",7);
        Book book1 = tem.persist(bookData1);
        Book bookData2 = new Book("978-7-5621-7275-8","book2",14);
        Book book2 = tem.persist(bookData2);
        bookId1 = book1.getBookId();
        bookId2 = book2.getBookId();
    }

    @Test
    public void findById() {
        Book foundBook = testBook.findById(bookId1);
        assertNotNull(foundBook);
        System.out.println(foundBook);
    }

    @Test
    public void findAll() {
        Collection<Book> allBooks = testBook.findAll();
        System.out.println(allBooks);
    }
    @Test
    public void create() {
        Book bookData3 = new Book("978-5-6416-3627-6","book3",30);
        Book book3 = testBook.create(bookData3);
        assertNotNull(book3);
        System.out.println(testBook.findAll());
    }

    @Test
    public void update() {
        Book sourceBook = tem.find(Book.class, bookId1);
        Book bookData4 = new Book("978-8-4130-7822-9","book4",21);
        Book book4 = testBook.create(bookData4);
        book4.setBookId(sourceBook.getBookId());
        Book updatedBook = testBook.update(book4);
        assertNotNull(updatedBook);
        System.out.println(sourceBook);
        System.out.println(updatedBook);
    }

    @Test
    public void delete() throws DataNotFoundException {
        Book foundBook = testBook.findById(bookId1);
        testBook.delete(foundBook.getBookId());
        System.out.println(testBook.findAll());
    }
}
