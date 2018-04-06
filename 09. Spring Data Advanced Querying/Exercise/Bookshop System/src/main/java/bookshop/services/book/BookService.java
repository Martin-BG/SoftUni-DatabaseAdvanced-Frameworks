package bookshop.services.book;

import bookshop.models.Book;

public interface BookService {

    void newBook(Book book);

    void newBooks(Iterable<Book> books);

    long getBooksCount();
}
