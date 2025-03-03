package org.icet.learn.repository.custom.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.icet.learn.dto.Login;
import org.icet.learn.repository.custom.LoginDao;
import org.icet.learn.util.AppModule;
import org.icet.learn.util.db.DBConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean searchLogin(Login login) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("select * from login where email=?");
            stm.setObject(1, login.getEmail());
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                if (login.getPassword().equals(res.getString(3))) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
