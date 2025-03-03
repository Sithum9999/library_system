package org.icet.learn.service.custom;

import javafx.collections.ObservableList;
import org.icet.learn.dto.User;
import org.icet.learn.service.SuperService;

public interface UserService extends SuperService {
    ObservableList<User> getAllUser();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(String id);

}
