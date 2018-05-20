package hiberspring.services;

import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.entities.Product;

public interface ProductService {

    Product createOne(ProductImportFromXmlDto productImportFromXmlDto);

}
