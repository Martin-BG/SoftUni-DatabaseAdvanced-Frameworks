package org.softuni.ruk.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private BigDecimal salary;

    private Date startedOn;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Branch branch;

    @ManyToMany
    @JoinTable(inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private Set<Client> clients = new HashSet<>();
}
