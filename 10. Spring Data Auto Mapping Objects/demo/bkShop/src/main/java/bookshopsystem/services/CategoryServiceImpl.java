package bookshopsystem.services;

import bookshopsystem.models.entity.Category;
import bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveIntoDb(List<Category> categories) {
        this.categoryRepository.save(categories);
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
