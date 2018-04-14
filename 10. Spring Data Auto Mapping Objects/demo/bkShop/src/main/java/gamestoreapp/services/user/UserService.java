package gamestoreapp.services.user;

import gamestoreapp.model.dto.binding.UserLoginBindingModel;
import gamestoreapp.model.dto.binding.UserRegisterBindingModel;
import gamestoreapp.model.dto.view.SuccessLoginUserViewModel;

public interface UserService {

    boolean register(UserRegisterBindingModel model);

    SuccessLoginUserViewModel login(UserLoginBindingModel model);


}