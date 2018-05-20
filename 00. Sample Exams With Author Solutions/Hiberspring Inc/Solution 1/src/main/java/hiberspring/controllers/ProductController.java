package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.dtos.imports.product.ProductsImportFromXmlDto;
import hiberspring.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import hiberspring.io.Writer;
import hiberspring.serializers.Serializer;
import hiberspring.services.ProductService;

@Controller
public class ProductController {
    private static final String PRODUCTS_XML_INPUT_PATH = "/xml/input/products.xml";

    private final Serializer xmlSerializer;
    private final ProductService productService;
    private final Writer writer;

    @Autowired
    public ProductController(@Qualifier("xml") Serializer xmlSerializer, ProductService productService, Writer writer) {
        this.xmlSerializer = xmlSerializer;
        this.productService = productService;
        this.writer = writer;
    }

    public void importProducts() {
        ProductsImportFromXmlDto productsImportFromXmlDto = this.xmlSerializer.deserialize(ProductsImportFromXmlDto.class, PRODUCTS_XML_INPUT_PATH);
        for (ProductImportFromXmlDto productImportFromXmlDto : productsImportFromXmlDto.getProductImportFromXmlDtos()) {
            Product product = this.productService.createOne(productImportFromXmlDto);
            if (product != null) {
                this.writer.println(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, product.getClass().getSimpleName(), product.getName());
            } else {
                this.writer.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
            }
        }
    }
}
