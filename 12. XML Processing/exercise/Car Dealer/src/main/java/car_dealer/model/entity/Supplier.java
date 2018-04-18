package car_dealer.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isImporter;
}
