package product_shop.utils;

import org.modelmapper.ModelMapper;


public class ModelMapperConfig {

    private final ModelMapper mapper;

    public ModelMapperConfig(final ModelMapper mapper) {
        this.mapper = mapper;
        this.initialize();
    }

    private void initialize() {
//        productNamePriceSellerName();
        // this.mapper.validate();
        // Add custom mappings directly or in private methods called here
//        this.mapper
//                .createTypeMap(User.class, UserDto.class)
//                .addMappings(mapper -> {
//                    mapper.map(src -> src.getSell().size() + 2,
//                            UserDto::setAge);
//                    mapper.map(src -> src.getBuy().size() + 7,
//                            UserDto::setFirstName);
//                });
    }

    private void productNamePriceSellerName() {
//        this.mapper.addConverter((Converter<Product, ProductNamePriceSellerNameDto>) context -> {
//            ProductNamePriceSellerNameDto dto = context.getDestination();
//            final User seller = context.getSource().getSeller();
//            if (seller.getFirstName() != null) {
//                dto.setSellerFullName(String.format("%s %s", seller.getFirstName(), seller.getLastName()));
//            } else {
//                dto.setSellerFullName(seller.getLastName());
//            }
//            return dto;
//        });

//        this.mapper
//                .createTypeMap(Product.class, ProductNamePriceSellerNameDto.class)
//                .addMappings(mapping -> mapping.map(src ->
//                                return String.format("%s %s", src.getSeller().getFirstName(), src.getSeller().getLastName()).trim(),
//                        ProductNamePriceSellerNameDto::setFullName));

//        this.mapper
//                .createTypeMap(Product.class, ProductNamePriceSellerNameDto.class)
//                .addMappings(mapping -> {
//                    mapping.map(src ->
//                                    String.format("%s %s", src.getSeller().getFirstName(), src.getSeller().getLastName()).trim(),
//                            ProductNamePriceSellerNameDto::setSellerFullName);
//                });
    }
}
