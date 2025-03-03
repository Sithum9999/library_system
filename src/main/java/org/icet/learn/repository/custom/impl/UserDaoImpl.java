package org.icet.learn.repository.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.icet.learn.entity.UserEntity;
import org.icet.learn.repository.custom.UserDao;
import org.icet.learn.util.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity entity) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into users (name, membership_date, number) values (?,?,?)");
            stm.setObject(1,entity.getName());
            stm.setObject(2,entity.getMembershipDate());
            stm.setObject(3,entity.getNumber());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("delete from users where id = ?");
            stm.setObject(1,id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        System.out.println(entity);
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE users SET name = ?, membership_date = ?, number = ? WHERE id = ?");
            stm.setObject(1,entity.getName());
            stm.setObject(2,entity.getMembershipDate());
            stm.setObject(3,entity.getNumber());
            stm.setObject(4,entity.getId());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        ObservableList<UserEntity> observableArrayList = FXCollections.observableArrayList();
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from users");
            while(res.next()){
                observableArrayList.add(new UserEntity(res.getInt(1),res.getString(2),res.getString(3),res.getString(4)));
            }
            return observableArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
