package org.icet.learn.service.custom;

import javafx.collections.ObservableList;
import org.icet.learn.dto.BookManage;
import org.icet.learn.service.SuperService;

public interface BorrowBookService extends SuperService {
    ObservableList<BookManage> getAllHistory();

    boolean borrowBook(BookManage book);
}
