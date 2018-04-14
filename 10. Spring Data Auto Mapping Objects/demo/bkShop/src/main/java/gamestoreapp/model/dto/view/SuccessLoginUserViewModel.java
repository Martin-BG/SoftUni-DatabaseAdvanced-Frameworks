package gamestoreapp.model.dto.view;

public class SuccessLoginUserViewModel {

    private String fullName;
    private Long id;

    public SuccessLoginUserViewModel() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
