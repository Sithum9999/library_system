package org.icet.learn.repository.custom;

import org.icet.learn.dto.BookManage;
import org.icet.learn.entity.BookManageEntity;

public interface ReturnBookDao {

    boolean returnBook(BookManageEntity bookManageEntity);
}
