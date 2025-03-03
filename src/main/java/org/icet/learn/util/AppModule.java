package org.icet.learn.util;


import com.google.inject.AbstractModule;
import org.icet.learn.repository.custom.*;
import org.icet.learn.repository.custom.impl.*;
import org.icet.learn.service.custom.*;
import org.icet.learn.service.custom.impl.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RegisterService.class).to(RegisterServiceImpl.class);
        bind(RegisterDao.class).to(RegisterDaoImpl.class);

        bind(LoginService.class).to(LoginServiceImpl.class);
        bind(LoginDao.class).to(LoginDaoImpl.class);

        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);

        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);

        bind(BorrowBookService.class).to(BorrowBookServiceImpl.class);
        bind(BorrowBookDao.class).to(BorrowBookDaoImpl.class);

        bind(ReturnBookService.class).to(ReturnBookServiceImpl.class);
        bind(ReturnBookDao.class).to(ReturnBookDaoImpl.class);

    }
}

