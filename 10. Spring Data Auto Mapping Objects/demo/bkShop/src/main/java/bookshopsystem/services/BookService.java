package bookshopsystem.services;

import bookshopsystem.models.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void saveIntoDb(List<Book> books);

    List<String> allTitlesAfterYear(Date year);
}
