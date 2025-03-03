package org.icet.learn.repository.custom.impl;

import javafx.scene.control.Alert;
import org.icet.learn.entity.RegisterEntity;
import org.icet.learn.repository.custom.RegisterDao;
import org.icet.learn.util.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDaoImpl implements RegisterDao {

    @Override
    public boolean addRegister(RegisterEntity register) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into login (email, password) values(?,?)");
            stm.setObject(1,register.getEmail());
            stm.setObject(2,register.getPassword());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
