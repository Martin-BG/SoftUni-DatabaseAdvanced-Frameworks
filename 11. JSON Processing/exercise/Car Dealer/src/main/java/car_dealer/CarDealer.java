package car_dealer;

import car_dealer.utils.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarDealer {

    public static void main(final String[] args) {
        SpringApplication.run(CarDealer.class, args);
    }

    @Bean
    public ModelMapper getMapper() {
        ModelMapper mapper = new ModelMapper();
        ModelMapperConfig config = new ModelMapperConfig(mapper);
        return mapper;
    }
}
