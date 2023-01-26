package g45_lexicon.spring_boot_workshop.dao.interfaces;


import g45_lexicon.spring_boot_workshop.entity.Details;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;

import java.util.Collection;

public interface DetailsDao {
    Details findById(int id);
    Collection<Details> findAll();
    Details create(Details details);
    Details update(Details details);
    void delete(int id) throws DataNotFoundException;
}
