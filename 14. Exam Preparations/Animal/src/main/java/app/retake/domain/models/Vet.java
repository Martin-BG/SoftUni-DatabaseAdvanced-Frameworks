package app.retake.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "vets")
public class Vet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    private String name;

    @Column(length = 50)
    private String profession;

    private Integer age;

    @Column(length = 13)
    private String phoneNumber;

    @OneToMany(mappedBy = "vet")
    private Set<Procedure> procedures;

    public Vet() {
        this.procedures = new HashSet<>();
    }
}
