package bookshop.models;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    private Long id;
    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;
    private Author author;
    private Set<Category> categories;

    public Book() {
        this.categories = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(length = 50, nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid book title");
        }

        this.title = title;
    }

    @Column(length = 1000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "edition_type", nullable = false)
    public EditionType getEditionType() {
        return this.editionType;
    }

    public void setEditionType(final EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        if (BigDecimal.ZERO.compareTo(price) > 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    @Column(nullable = false)
    public Integer getCopies() {
        return this.copies;
    }

    public void setCopies(final Integer copies) {
        this.copies = copies;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(final LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "age_restriction", nullable = false)
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(final AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @ManyToOne(optional = false)//, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    @ManyToMany//(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }
}
