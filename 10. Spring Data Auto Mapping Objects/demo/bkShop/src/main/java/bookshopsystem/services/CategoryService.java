package bookshopsystem.services;

import bookshopsystem.models.entity.Category;

import java.util.List;

public interface CategoryService {

    void saveIntoDb(List<Category> categories);

    List<Category> getAll();
}
