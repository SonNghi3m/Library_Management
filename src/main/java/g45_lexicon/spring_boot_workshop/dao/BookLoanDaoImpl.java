package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.dao.interfaces.BookLoanDao;
import g45_lexicon.spring_boot_workshop.entity.BookLoan;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookLoanDaoImpl implements BookLoanDao {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(int id) {return em.find(BookLoan.class, id);}

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {return em.createQuery("select bl from BookLoan bl", BookLoan.class).getResultList();}

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        em.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        em.merge(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
    BookLoan bookLoan = em.find(BookLoan.class, id);
    if (bookLoan != null) em.remove(bookLoan);
    else throw new DataNotFoundException("data was not found");
    }
}
