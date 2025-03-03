package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import org.icet.learn.dto.Login;
import org.icet.learn.entity.LoginEntitiy;
import org.icet.learn.repository.custom.LoginDao;
import org.icet.learn.service.custom.LoginService;
import org.modelmapper.ModelMapper;

public class LoginServiceImpl implements LoginService {

    @Inject
    LoginDao dao;

    @Override
    public boolean searchLogin(Login login) {
        LoginEntitiy loginEntitiy = new ModelMapper().map(login, LoginEntitiy.class);
        boolean isAdded = dao.searchLogin(login);
        return isAdded;
    }
}
