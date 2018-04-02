package bookshop.services.category;

import bookshop.models.Category;
import bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void newCategory(final Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void newCategories(final Iterable<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public Set<Category> getRandomCategories() {
        long count = this.categoryRepository.count();
        if (count < 1L) {
            throw new RuntimeException("No categories in database");
        }

        List<Category> categories = this.categoryRepository.findAll();
        Random random = new Random();
        Set<Category> result = new HashSet<>();

        while (random.nextInt(2) > 0) {
            int index = (int) ((random.nextLong() & Long.MAX_VALUE) % count); // Ensure positive index (random can produce negative numbers)
            result.add(categories.get(index));
        }

        return result;
    }
}
