package game_store.model.dto.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameFullViewDto {

    private String title;
    private BigDecimal price;
    private BigDecimal size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameFullViewDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(final BigDecimal size) {
        this.size = size;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(final String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(final String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(final LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
