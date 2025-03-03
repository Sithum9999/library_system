package org.icet.learn.service.custom.impl;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.icet.learn.dto.User;
import org.icet.learn.entity.UserEntity;
import org.icet.learn.repository.custom.UserDao;
import org.icet.learn.service.custom.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    @Override
    public ObservableList<User> getAllUser() {
        ObservableList<UserEntity> all = dao.getAll();
        ObservableList<User> objects = FXCollections.observableArrayList();
        all.forEach(e->{
            objects.add( new ModelMapper().map(e, User.class));
        });
        return objects;
    }

    @Override
    public boolean addUser(User user) {
        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        boolean isUserAdded= dao.save(userEntity);
        return isUserAdded;

    }

    @Override
    public boolean updateUser(User user) {

        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        boolean isUpdated = dao.update(userEntity);
        return isUpdated;
    }

    @Override
    public boolean deleteUser(String id) {
        boolean isDeleted = dao.delete(id);
        return isDeleted;
    }

}
