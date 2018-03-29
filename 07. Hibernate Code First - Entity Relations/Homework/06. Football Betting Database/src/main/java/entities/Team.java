package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team implements Serializable {

    private static final long serialVersionUID = 313161999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    @Lob
    @Column(length = 50000)
    private byte[] logo;

    @Column(length = 3)
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color_id")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color_id")
    private Color secondaryKitColor;

    @ManyToOne
    private Town town;

    private BigDecimal budget;

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

    public byte[] getLogo() {
        return this.logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return this.initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return this.primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return this.secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public BigDecimal getBudget() {
        return this.budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
