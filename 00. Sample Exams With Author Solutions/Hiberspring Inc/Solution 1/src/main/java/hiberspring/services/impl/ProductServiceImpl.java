package hiberspring.services.impl;

import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.entities.Branch;
import hiberspring.entities.Product;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.ProductRepository;
import hiberspring.services.ProductService;
import hiberspring.utilities.MapperConverter;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, MapperConverter mapperConverter) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public Product createOne(ProductImportFromXmlDto productImportFromXmlDto) {
        Product product = this.mapperConverter.convert(productImportFromXmlDto, Product.class);
        Branch branch = null;
        if (product.getBranch() != null) {
            branch = this.branchRepository.getBranchByName(product.getBranch().getName());
        }
        product.setBranch(branch);

        if (EntityValidator.isValid(product)) {
            product = this.productRepository.save(product);
        }
        return product;
    }

}
