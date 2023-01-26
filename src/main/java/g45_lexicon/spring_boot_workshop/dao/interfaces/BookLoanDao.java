package g45_lexicon.spring_boot_workshop.dao.interfaces;

import g45_lexicon.spring_boot_workshop.entity.BookLoan;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;

import java.util.Collection;

public interface BookLoanDao {
    BookLoan findById(int id);
    Collection<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    void delete(int id) throws DataNotFoundException;
}
