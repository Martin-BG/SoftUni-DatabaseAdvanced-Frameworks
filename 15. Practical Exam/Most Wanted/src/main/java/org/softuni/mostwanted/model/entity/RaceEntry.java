package org.softuni.mostwanted.model.entity;

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
@Table(name = "race_entities")
public class RaceEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean hasFinished;

    private Double finishTime;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Racer racer;

    @ManyToOne
    private Race race;
}
