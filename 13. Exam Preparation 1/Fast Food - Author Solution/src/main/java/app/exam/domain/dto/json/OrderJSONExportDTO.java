package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

public class OrderJSONExportDTO {
    @Expose
    private String customer;

    @Expose
    private ItemJSONExportDTO[] items;

    public OrderJSONExportDTO() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ItemJSONExportDTO[] getItems() {
        return items;
    }

    public void setItems(ItemJSONExportDTO[] items) {
        this.items = items;
    }
}
