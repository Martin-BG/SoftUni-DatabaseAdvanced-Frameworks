package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "positions")
public class Position implements Serializable {

    private static final long serialVersionUID = 222344198789235L;

    @Id
    @Column(nullable = false, unique = true, length = 2)
    private String id;

    @Column(length = 30)
    private String description;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
