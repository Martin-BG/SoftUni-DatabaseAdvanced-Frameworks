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
@Table(name = "animals")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String type;

    private Integer age;

    @OneToOne
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private Set<Procedure> procedures;

    public Animal() {
        this.procedures = new HashSet<>();
    }
}
