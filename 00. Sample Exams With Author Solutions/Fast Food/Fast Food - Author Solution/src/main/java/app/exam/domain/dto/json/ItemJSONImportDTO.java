package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ItemJSONImportDTO implements Serializable {
    @Expose
    @Size(min = 3, max = 30)
    private String name;

    @Expose
    @NotNull
    @DecimalMin("0.01")
    private Double price;

    @Expose
    @Size(min = 3, max = 30)
    @NotNull
    private String category;

    public ItemJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
