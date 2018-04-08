package game_store.model.entities;

import game_store.constants.Tables;
import game_store.constants.ValidationConstrains;
import game_store.model.enums.Role;
import game_store.model.validators.email.Email;
import game_store.model.validators.password.Password;
import game_store.model.validators.user_name.UserName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.TABLE_USERS)
public class User {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Role role;
    private Set<Game> games;
    private Set<Game> shoppingCart;

    public User() {
        this.games = new HashSet<>();
        this.shoppingCart = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(unique = true,
            nullable = ValidationConstrains.USER_EMAIL_CAN_BE_EMPTY,
            length = ValidationConstrains.USER_EMAIL_MAX_LENGTH)
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(nullable = ValidationConstrains.USER_PASSWORD_CAN_BE_OMITTED,
            length = ValidationConstrains.USER_PASSWORD_MAX_LENGTH)
    @Password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Column(length = ValidationConstrains.USER_NAME_MAX_LENGTH)
    @UserName
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    @Column(nullable = false,
            length = Tables.ROLE_COLUMN_LENGTH)
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return this.role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = Tables.TABLE_USERS_OWNED_GAMES,
            inverseJoinColumns = @JoinColumn(name = Tables.COLUMN_GAME_ID))
    public Set<Game> getGames() {
        return this.games;
    }

    public void setGames(final Set<Game> games) {
        this.games = games;
    }

    @ManyToMany
    @JoinTable(name = Tables.TABLE_USERS_SHOPPING_CART_GAMES,
            inverseJoinColumns = @JoinColumn(name = Tables.COLUMN_GAME_ID))
    public Set<Game> getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(final Set<Game> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
