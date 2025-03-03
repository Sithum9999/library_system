package org.icet.learn.repository.custom;

import javafx.collections.ObservableList;
import org.icet.learn.entity.BookManageEntity;

public interface BorrowBookDao  {
    ObservableList<BookManageEntity> getAllHistory();

    boolean borrowBook(BookManageEntity borrowBookEntity);
}
