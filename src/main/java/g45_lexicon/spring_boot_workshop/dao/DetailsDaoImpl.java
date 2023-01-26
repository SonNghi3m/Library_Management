package g45_lexicon.spring_boot_workshop.dao;



import g45_lexicon.spring_boot_workshop.dao.interfaces.DetailsDao;
import g45_lexicon.spring_boot_workshop.entity.Details;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class DetailsDaoImpl implements DetailsDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional(readOnly = true)
    public Details findById(int id) {
        return entityManager.find(Details.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createQuery("select d from Details d", Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        entityManager.merge(details);
        return details;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException{
        Details details = entityManager.find(Details.class, id);
        if (details != null) entityManager.remove(details);
        else throw new DataNotFoundException("details was not found!");
    }
}
