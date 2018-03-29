package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "results_predictions")
public class ResultPrediction implements Serializable {

    private static final long serialVersionUID = 333666999701985L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PredictionEnum prediction;
}
