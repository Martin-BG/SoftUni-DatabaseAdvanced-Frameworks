package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchImportFromJsonDto {

    @Expose
    private String name;

    @Expose
    @SerializedName("town")
    private String townName;

    public BranchImportFromJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
