package game_store.model.dto.binding;

import game_store.model.validators.game_description.GameDescription;
import game_store.model.validators.game_price.GamePrice;
import game_store.model.validators.game_size.GameSize;
import game_store.model.validators.game_title.GameTitle;
import game_store.model.validators.game_trailer.GameTrailer;
import game_store.model.validators.thumbnail_url.ThumbnailUrl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDto {

    @GameTitle
    private String title;

    @GamePrice
    private BigDecimal price;

    @GameSize
    private BigDecimal size;

    @GameTrailer
    private String trailer;

    @ThumbnailUrl
    private String thumbnailUrl;

    @GameDescription
    private String description;

    private LocalDate releaseDate;

    public AddGameDto() {
    }

    public AddGameDto(final String title, final BigDecimal price,
                      final BigDecimal size, final String trailer,
                      final String thumbnailUrl, final String description,
                      final LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
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
