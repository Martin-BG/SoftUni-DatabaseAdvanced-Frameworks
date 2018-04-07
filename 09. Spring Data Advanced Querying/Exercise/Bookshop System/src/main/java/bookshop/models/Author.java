package bookshop.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedStoredProcedureQuery(name = "udp_find_books_by_author",
        procedureName = "udp_find_books_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "first_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "last_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "books_count", type = Integer.class)
        })
public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Author's last name cannot be empty");
        }

        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "author")
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final Set<Book> books) {
        this.books = books;
    }
}
