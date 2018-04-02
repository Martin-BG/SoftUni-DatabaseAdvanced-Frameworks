package user.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {

    private Long id;
    private String title;
    private String caption;
    private String pathOnFileSystem;
    private Set<Album> albums;

    public Picture() {
        this.albums = new HashSet<>();
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getPathOnFileSystem() {
        return this.pathOnFileSystem;
    }

    public void setPathOnFileSystem(final String pathOnFileSystem) {
        this.pathOnFileSystem = pathOnFileSystem;
    }

    @ManyToMany()
    public Set<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }
}
