package g45_lexicon.spring_boot_workshop.dao.interfaces;

import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Author;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;

import java.util.Collection;

public interface AuthorDao {
    Author findById(int id);
    Collection<Author> findAll();
    Author create(Author author);
    Author update(Author author);
    void delete(int id) throws DataNotFoundException;
}
