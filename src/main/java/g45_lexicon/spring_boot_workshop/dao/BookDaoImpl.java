package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.dao.interfaces.BookDao;
import g45_lexicon.spring_boot_workshop.entity.Book;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    EntityManager em;
    @Override
    @Transactional(readOnly = true)
    public Book findById(int id) {return em.find(Book.class, id);}
    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {return em.createQuery("select b from Book b", Book.class).getResultList();}
    @Override
    @Transactional
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        em.merge(book);
        return book;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
    Book book = em.find(Book.class, id);
    if (book != null) em.remove(book);
    else throw new DataNotFoundException("book was not found");
    }
}
