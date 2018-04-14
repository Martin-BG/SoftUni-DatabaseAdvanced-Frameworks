package gamestoreapp.config;

import gamestoreapp.model.dto.binding.UserRegisterBindingModel;
import gamestoreapp.model.entity.User;
import org.modelmapper.ModelMapper;

public class ModelMapperConfig {

    private final ModelMapper modelMapper;

    public ModelMapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {
        this.userRegisterMapping();
    }

    private void userRegisterMapping() {
        this.modelMapper.createTypeMap(UserRegisterBindingModel.class, User.class)
                .addMappings(mapper -> {
                    mapper.map(UserRegisterBindingModel::getA, User::setEmail);
                    mapper.map(UserRegisterBindingModel::getB, User::setFullName);
                });
    }
}
