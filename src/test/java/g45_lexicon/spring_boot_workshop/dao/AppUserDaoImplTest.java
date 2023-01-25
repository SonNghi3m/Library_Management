package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Details;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
public class AppUserDaoImplTest {
    @Autowired
    TestEntityManager tem;
    @Autowired
    AppUserDaoImpl testAppUser;


    int detailsDataId1;
    int detailsDataId2;
    int appUserId1;
    int appUserId2;


    @BeforeEach // before each test, do the following first:...
    public void setup() {
        Details detailsData1 = new Details("email1","Full Name1", LocalDate.parse("2023-01-01"));
        AppUser appUserData1 = new AppUser("user1","pass1",detailsData1);
        AppUser appUser1 = tem.persist(appUserData1);
        Details detailsData2 = new Details("email2","Full Name2", LocalDate.parse("2023-01-02"));
        AppUser appUserData2 = new AppUser("user2","pass2",detailsData2);
        AppUser appUser2 = tem.persist(appUserData2);
        appUserId1 = appUser1.getAppUserId();
        appUserId2 = appUser2.getAppUserId();
        detailsDataId1 = detailsData1.getDetailsId();
        detailsDataId2 = detailsData2.getDetailsId();
    }

    @Test
    public void findById() {
        AppUser foundAppUser = testAppUser.findById(appUserId1);
        assertNotNull(foundAppUser);
        System.out.println(foundAppUser);
    }
    @Test
    public void findAll() {
        Collection<AppUser> allAppUsers = testAppUser.findAll();
        System.out.println(allAppUsers);
    }
    @Test
    public void create() {
        Details detailsData3 = new Details("email3","Full Name3", LocalDate.parse("2023-01-03"));
        AppUser appUserData3 = new AppUser("user3","pass3",detailsData3);
        AppUser appUser = testAppUser.create(appUserData3);

        assertNotNull(appUser);
        System.out.println(testAppUser.findAll());

    }

    @Test
    public void update() {
        AppUser sourceAppUser = tem.find(AppUser.class, appUserId1);
        Details newDetailsData = new Details("email33","Full Name33", LocalDate.parse("2023-01-30"));
        AppUser newAppUserData = new AppUser("user33","pass33",newDetailsData);
        AppUser appUser = testAppUser.create(newAppUserData);
        newDetailsData.setDetailsId(sourceAppUser.getDetails().getDetailsId());
        newAppUserData.setAppUserId(sourceAppUser.getAppUserId());
        AppUser updatedAppUser = testAppUser.update(appUser);
        assertNotNull(updatedAppUser);
        System.out.println(sourceAppUser);
        System.out.println(updatedAppUser);
    }
    @Test
    public void delete() throws DataNotFoundException {
        AppUser foundUser = testAppUser.findById(appUserId1);
        testAppUser.delete(foundUser.getAppUserId());
        System.out.println(testAppUser.findAll());
    }

}
