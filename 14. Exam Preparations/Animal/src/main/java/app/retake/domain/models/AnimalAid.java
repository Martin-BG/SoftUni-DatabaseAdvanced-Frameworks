package app.retake.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "animal_aids")
public class AnimalAid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    private Double price;

    @ManyToMany
    @JoinTable(name = "animal_aids_procedures",
            joinColumns = {@JoinColumn(name = "animal_aid_id")},
            inverseJoinColumns = {@JoinColumn(name = "procedure_id")})
    private Set<Procedure> procedures;
}
