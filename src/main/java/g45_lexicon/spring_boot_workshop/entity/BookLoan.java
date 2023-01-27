package g45_lexicon.spring_boot_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    @Column(nullable = false)
    private LocalDate loanDate;
    @Column(nullable = false)
    private LocalDate dueDate;
    @Column(nullable = false)
    private boolean returned;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "appUser_id")
    private AppUser borrower;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;
    @OneToMany(mappedBy = "bookLoan", orphanRemoval = true)
    private List<AppUser> borrowers;
    @OneToMany(mappedBy = "bookLoan", orphanRemoval = true)
    private List<Book> books;

    //constructors
    public BookLoan() {
    }

    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, List<AppUser> borrowers, List<Book> books) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrowers = borrowers;
        this.books = books;
    }

//getters and setters

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public List<AppUser> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<AppUser> borrower) {
        this.borrowers = borrower;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    //methods
    public void addBorrower(AppUser borrower) {
        if (borrower == null) throw new IllegalArgumentException("borrower data was null");
        if (borrowers == null) borrowers = new ArrayList<>();
        borrowers.add(borrower);
        borrower.setBookLoan(this);
    }

    public void removeBorrower(AppUser borrower) {
        if (borrower == null) throw new IllegalArgumentException("borrower data was null");
        if (borrowers != null) {
            borrower.setBookLoan(null);
            borrowers.remove(borrower);
        }
    }

    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book data was null");
        if (books == null) books = new ArrayList<>();
        books.add(book);
        book.setBookLoan(this);
    }

    public void removeBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book data was null");
        if (books != null) {
            book.setBookLoan(null);
            books.remove(book);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return loanId == bookLoan.loanId && returned == bookLoan.returned && Objects.equals(loanDate, bookLoan.loanDate) && Objects.equals(dueDate, bookLoan.dueDate) && Objects.equals(borrowers, bookLoan.borrowers) && Objects.equals(books, bookLoan.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanDate, dueDate, returned, borrowers, books);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId=" + loanId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrowers=" + borrowers +
                ", books=" + books +
                '}';
    }
}


