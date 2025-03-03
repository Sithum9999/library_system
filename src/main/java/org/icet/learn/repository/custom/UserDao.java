package org.icet.learn.repository.custom;

import org.icet.learn.entity.UserEntity;
import org.icet.learn.repository.CrudRepository;
import org.icet.learn.repository.SuperDao;

public interface UserDao extends CrudRepository<UserEntity,String> {

}
