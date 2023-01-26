package g45_lexicon.spring_boot_workshop.dao.interfaces;

import g45_lexicon.spring_boot_workshop.entity.Book;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;

import java.util.Collection;

public interface BookDao {
    Book findById(int id);
    Collection<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(int id) throws DataNotFoundException;

}
