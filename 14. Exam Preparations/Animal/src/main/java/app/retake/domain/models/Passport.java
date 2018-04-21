package app.retake.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pasports")
public class Passport implements Serializable {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String serialNumber;

    @OneToOne(mappedBy = "passport")
    private Animal animal;

    @Column(length = 13, nullable = false)
    private String ownerPhoneNumber;

    @Column(length = 30)
    private String ownerName;

    @Column(name = "registered_on")
    private Date registrationDate;
}
