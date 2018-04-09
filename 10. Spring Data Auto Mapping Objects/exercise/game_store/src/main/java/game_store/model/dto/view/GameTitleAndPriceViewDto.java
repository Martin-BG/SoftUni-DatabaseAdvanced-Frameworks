package game_store.model.dto.view;

import game_store.model.validators.game_price.GamePrice;
import game_store.model.validators.game_title.GameTitle;

import java.math.BigDecimal;

public class GameTitleAndPriceViewDto {

    @GameTitle
    private String title;

    @GamePrice
    private BigDecimal price;

    public GameTitleAndPriceViewDto() {
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
}
