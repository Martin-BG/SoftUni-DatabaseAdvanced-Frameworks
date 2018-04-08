package game_store.model.entities;

import game_store.constants.Tables;
import game_store.constants.ValidationConstrains;
import game_store.model.validators.game_description.GameDescription;
import game_store.model.validators.game_price.GamePrice;
import game_store.model.validators.game_size.GameSize;
import game_store.model.validators.game_title.GameTitle;
import game_store.model.validators.game_trailer.GameTrailer;
import game_store.model.validators.thumbnail_url.ThumbnailUrl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = Tables.TABLE_GAMES)
public class Game {

    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public Game() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(unique = true,
            nullable = ValidationConstrains.GAME_TITLE_CAN_BE_OMITTED,
            length = ValidationConstrains.GAME_TITLE_MAX_LENGTH)
    @GameTitle
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    @GamePrice
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @Column(precision = 10, scale = 1)
    @GameSize
    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(final BigDecimal size) {
        this.size = size;
    }

    @Column(length = ValidationConstrains.GAME_TRAILER_MAX_LENGTH)
    @GameTrailer
    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(final String trailerId) {
        this.trailer = trailerId;
    }

    @Column(nullable = ValidationConstrains.GAME_THUMBNAIL_URL_CAN_BE_OMITTED,
            length = ValidationConstrains.GAME_THUMBNAIL_URL_MAX_LENGTH)
    @ThumbnailUrl
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(final String thumbnailURL) {
        this.thumbnailUrl = thumbnailURL;
    }

    @Column(nullable = ValidationConstrains.GAME_DESC_CAN_BE_OMITTED,
            length = ValidationConstrains.GAME_DESC_MAX_LENGTH)
    @GameDescription
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
