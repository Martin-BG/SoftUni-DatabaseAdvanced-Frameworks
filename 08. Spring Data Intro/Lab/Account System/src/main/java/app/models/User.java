package app.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private long id;
    private String userName;
    private int age;
    private Set<Account> accounts;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Column(name = "user_name", length = 40, unique = true, nullable = false)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Column(nullable = false)
    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(final Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
