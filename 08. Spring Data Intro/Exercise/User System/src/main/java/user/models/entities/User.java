package user.models.entities;

import org.hibernate.validator.constraints.Length;
import user.annotations.email.Email;
import user.annotations.password.Password;
import user.constants.TextConstants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private byte[] profilePicture;
    private Date registeredOn;
    private Date lastTimeLoggedIn;
    private Integer age;
    private Boolean isDeleted;
    private Town bornTown;
    private Town currentlyLiving;
    private String firstName;
    private String lastName;
    private Set<User> friends;
    private Set<Album> albums;

    public User() {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 30, message = TextConstants.USERNAME_INCORRECT_LENGTH)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Column(nullable = false)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbols = true)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
/*    @Pattern(
            regexp = "^[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+@[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9](\\.[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+)+$",
            message = "Invalid email address")*/
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Lob
    @Size(max = 1024 * 1024)
    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(final byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(final Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return this.lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(final Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Min(value = 1, message = TextConstants.AGE_TOO_LOW)
    @Max(value = 120, message = TextConstants.AGE_TOO_HIGH)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(final Boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    public Town getBornTown() {
        return this.bornTown;
    }

    public void setBornTown(final Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne
    public Town getCurrentlyLiving() {
        return this.currentlyLiving;
    }

    public void setCurrentlyLiving(final Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(final Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany
    public Set<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }
}
