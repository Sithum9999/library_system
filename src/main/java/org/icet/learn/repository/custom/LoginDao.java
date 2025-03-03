package org.icet.learn.repository.custom;

import org.icet.learn.dto.Login;
import org.icet.learn.repository.CrudRepository;
import org.icet.learn.repository.SuperDao;

public interface LoginDao extends SuperDao {
    boolean searchLogin(Login login);
}
