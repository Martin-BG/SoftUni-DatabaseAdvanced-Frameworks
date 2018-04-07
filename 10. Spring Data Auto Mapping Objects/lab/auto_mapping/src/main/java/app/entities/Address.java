package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {

    private Long id;
    private String city;
    private String country;
    private Set<Employee> employees;

    public Address() {
        this.employees = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(final Set<Employee> employees) {
        this.employees = employees;
    }
}
