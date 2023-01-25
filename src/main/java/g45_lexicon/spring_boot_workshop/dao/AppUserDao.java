package g45_lexicon.spring_boot_workshop.dao;


import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;

import javax.xml.bind.DataBindingException;
import java.util.Collection;

public interface AppUserDao {
    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id) throws DataNotFoundException;
}
