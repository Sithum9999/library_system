package org.icet.learn.repository.custom;

import org.icet.learn.entity.BookEntity;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.CrudRepository;

public interface BookDao extends CrudRepository<BookEntity,String> {

    boolean updateStatus(BookManageEntity book, String status);
}
