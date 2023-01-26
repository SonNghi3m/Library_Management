package g45_lexicon.spring_boot_workshop.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailsId;
    @Column(nullable = false,length = 100, unique = true)
    private String email;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne(mappedBy = "details")
    private AppUser appUser;

    //constructors
    public Details() {}
    public Details(String email, String name, LocalDate birthDate) {
        this();
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }



    //setters & getters

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    //equal & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return detailsId == details.detailsId && Objects.equals(email, details.email) && Objects.equals(name, details.name) && Objects.equals(birthDate, details.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailsId, email, name, birthDate);
    }

    //toString

    @Override
    public String toString() {
        return "Details{" +
                "detailsId=" + detailsId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
