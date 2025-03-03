package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import org.icet.learn.dto.Register;
import org.icet.learn.entity.BookEntity;
import org.icet.learn.entity.RegisterEntity;
import org.icet.learn.entity.UserEntity;
import org.icet.learn.repository.custom.RegisterDao;
import org.icet.learn.service.custom.RegisterService;
import org.modelmapper.ModelMapper;

public class RegisterServiceImpl implements RegisterService {

    @Inject
    RegisterDao dao;

    @Override
    public boolean addLogin(Register register) {
        RegisterEntity registerEntity = new ModelMapper().map(register, RegisterEntity.class);
        boolean isRegisterd = dao.addRegister(registerEntity);
        return isRegisterd;
    }
}
