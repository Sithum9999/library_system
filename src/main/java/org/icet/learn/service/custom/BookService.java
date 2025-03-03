package org.icet.learn.service.custom;

import javafx.collections.ObservableList;
import org.icet.learn.dto.Book;
import org.icet.learn.dto.BookManage;
import org.icet.learn.service.SuperService;

public interface BookService extends SuperService {

    ObservableList<Book> getAllBook();

    boolean addBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBook(String id);

    void updateStatus(BookManage book, String status);
}
