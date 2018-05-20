package hiberspring.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @NotNull
    @Column(name = "product_name")
    private String name;

    @NotNull
    @Column(name = "count_of_clients")
    private Integer countOfClients;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Product() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountOfClients() {
        return this.countOfClients;
    }

    public void setCountOfClients(Integer countOfClients) {
        this.countOfClients = countOfClients;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
