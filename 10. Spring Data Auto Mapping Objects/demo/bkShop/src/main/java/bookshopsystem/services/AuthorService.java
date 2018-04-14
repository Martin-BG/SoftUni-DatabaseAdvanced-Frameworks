package bookshopsystem.services;

import bookshopsystem.models.entity.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    void saveAuthorIntoDb(List<Author> authors);

    List<Author> getAll();
}
