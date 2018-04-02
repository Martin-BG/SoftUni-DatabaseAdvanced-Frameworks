package user.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    private Long id;
    private String name;
    private String backgroundColor;
    private Boolean isPublic;
    private Set<Picture> pictures;
    private User owner;

    public Album() {
        this.pictures = new HashSet<>();
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(final Boolean aPublic) {
        isPublic = aPublic;
    }

    @ManyToMany(mappedBy = "albums")
    public Set<Picture> getPictures() {
        return this.pictures;
    }

    public void setPictures(final Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToOne(optional = false)
    public User getOwner() {
        return this.owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }
}
