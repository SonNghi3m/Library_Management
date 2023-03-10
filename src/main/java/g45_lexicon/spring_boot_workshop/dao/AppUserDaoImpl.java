package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.dao.interfaces.AppUserDao;
import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AppUserDaoImpl implements AppUserDao {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(int id) {
        return em.find(AppUser.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return em.createQuery("select au from AppUser au", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        em.merge(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
        AppUser appUser = em.find(AppUser.class, id);
        if (appUser != null) em.remove(appUser);
        else throw new DataNotFoundException("user was not found!");
    }
}
