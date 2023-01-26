package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.dao.interfaces.AuthorDao;
import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Author;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    EntityManager em;
    @Override
    @Transactional(readOnly = true)
    public Author findById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        em.merge(author);
        return author;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
        Author author = em.find(Author.class, id);
        if (author != null) em.remove(author);
        else throw new DataNotFoundException("user was not found!");
    }
}
