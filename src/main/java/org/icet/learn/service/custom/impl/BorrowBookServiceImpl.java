package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.icet.learn.dto.BookManage;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.BorrowBookDao;
import org.icet.learn.service.custom.BorrowBookService;
import org.modelmapper.ModelMapper;

public class BorrowBookServiceImpl implements BorrowBookService {

    @Inject
    private BorrowBookDao dao;

    @Override
    public ObservableList<BookManage> getAllHistory() {
        ObservableList<BookManageEntity> allHistory = dao.getAllHistory();
        ObservableList<BookManage> objects = FXCollections.observableArrayList();
        allHistory.forEach(e->{
            objects.add( new ModelMapper().map(e, BookManage.class));
        });
        return objects;
    }

    @Override
    public boolean borrowBook(BookManage book) {
        BookManageEntity borrowBookEntity = new ModelMapper().map(book, BookManageEntity.class);
        return dao.borrowBook(borrowBookEntity);
    }
}
