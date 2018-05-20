package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelParser modelParser;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        Set<ConstraintViolation<ItemJSONImportDTO>> constraintViolationSet =
                this.validator.validate(itemJSONImportDTO);
        if (constraintViolationSet.size() != 0) {
            throw new IllegalArgumentException();
        }
        if (this.itemsRepository.findByName(itemJSONImportDTO.getName()) != null) {
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(itemJSONImportDTO.getCategory());
            this.categoryRepository.saveAndFlush(category);
        }
        Item item = this.modelParser.convert(itemJSONImportDTO, Item.class);
        item.setCategory(category);
        this.itemsRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public Double updateItemPrice(String itemName, Double price) {
        Item item = this.itemsRepository.findByName(itemName);
        Double oldPrice = item.getPrice().doubleValue();
        if (price < 0) {
            this.itemsRepository.updateItemPrice(itemName, new BigDecimal(price));
        }
        return oldPrice;
    }
}
