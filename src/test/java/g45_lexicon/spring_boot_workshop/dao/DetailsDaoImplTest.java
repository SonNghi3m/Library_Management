package g45_lexicon.spring_boot_workshop.dao;

import g45_lexicon.spring_boot_workshop.entity.AppUser;
import g45_lexicon.spring_boot_workshop.entity.Details;
import g45_lexicon.spring_boot_workshop.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
public class DetailsDaoImplTest {
    @Autowired
    TestEntityManager tem;
    @Autowired
    DetailsDaoImpl testDetails;

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
        Details foundDetails = testDetails.findById(detailsDataId1);
        assertNotNull(foundDetails);
        System.out.println(foundDetails);
    }
    @Test
    public void findAll() {
        Collection<Details> allDetails = testDetails.findAll();
        System.out.println(allDetails);
    }
    @Test
    public void create() {
        Details detailsData3 = new Details("email3","Full Name3", LocalDate.parse("2023-01-03"));
        Details details = testDetails.create(detailsData3);
        assertNotNull(details);
        System.out.println(testDetails.findAll());
    }

    @Test
    public void update() {
        Details sourceDetails = tem.find(Details.class, detailsDataId1);
        Details updatedDetails = sourceDetails;
        updatedDetails.setName("Updated name");
        testDetails.update(updatedDetails);
        assertEquals(updatedDetails,sourceDetails);
        System.out.println(testDetails.findAll());
    }
    @Test
    public void delete() throws DataNotFoundException {
    Details foundDetails = testDetails.findById(detailsDataId1);
    testDetails.delete(foundDetails.getDetailsId());
        System.out.println(testDetails.findAll());
    }


}
