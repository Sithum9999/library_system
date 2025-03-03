package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import org.icet.learn.dto.BookManage;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.ReturnBookDao;
import org.icet.learn.service.custom.ReturnBookService;
import org.modelmapper.ModelMapper;

public class ReturnBookServiceImpl implements ReturnBookService {

    @Inject
    private ReturnBookDao dao;

    @Override
    public boolean returnBook(BookManage returnBook) {
        BookManageEntity bookManageEntity = new ModelMapper().map(returnBook, BookManageEntity.class);
        return dao.returnBook(bookManageEntity);
    }
}
