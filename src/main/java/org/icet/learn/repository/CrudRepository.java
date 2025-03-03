package org.icet.learn.repository;

import javafx.collections.ObservableList;
import org.icet.learn.entity.UserEntity;

public interface CrudRepository <T,ID> extends SuperDao{

    boolean save(T entity);

    boolean delete(ID id);

    boolean update(T entity);

    ObservableList<T> getAll();

}
