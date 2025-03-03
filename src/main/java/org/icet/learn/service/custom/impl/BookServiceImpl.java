package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.icet.learn.dto.Book;
import org.icet.learn.dto.BookManage;
import org.icet.learn.entity.BookEntity;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.BookDao;
import org.icet.learn.service.custom.BookService;
import org.modelmapper.ModelMapper;

public class BookServiceImpl implements BookService {

    @Inject
    private BookDao dao;

    @Override
    public ObservableList<Book> getAllBook() {
        ObservableList<BookEntity> all = dao.getAll();
        ObservableList<Book> objects = FXCollections.observableArrayList();
       all.forEach(e->{
            objects.add( new ModelMapper().map(e, Book.class));
        });
        return objects;
    }

    @Override
    public boolean addBook(Book book) {
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);
        boolean isBookAdded= dao.save(bookEntity);
        return isBookAdded;
    }

    @Override
    public boolean updateBook(Book book) {
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);
        boolean isUpdated = dao.update(bookEntity);
        return isUpdated;
    }

    @Override
    public boolean deleteBook(String id) {
        boolean isDeleted = dao.delete(id);
        return isDeleted;
    }

    @Override
    public void updateStatus(BookManage book, String status) {
        dao.updateStatus(new ModelMapper().map(book, BookManageEntity.class),status);
    }
}
