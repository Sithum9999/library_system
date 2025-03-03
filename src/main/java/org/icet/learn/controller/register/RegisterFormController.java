package org.icet.learn.controller.register;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.icet.learn.dto.Book;
import org.icet.learn.dto.Register;
import org.icet.learn.dto.User;
import org.icet.learn.repository.custom.RegisterDao;
import org.icet.learn.service.custom.RegisterService;
import org.icet.learn.service.custom.impl.RegisterServiceImpl;

public class RegisterFormController {

        @FXML
        private JFXTextField txtConfirmedPassword;

        @FXML
        private JFXTextField txtEmail;

        @FXML
        private JFXTextField txtPassword;

        @Inject
        private RegisterService service;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        Register register = new Register(txtEmail.getText(), txtPassword.getText(), txtConfirmedPassword.getText());
        System.out.println(register);
        boolean isRegisterd = service.addLogin(register);
        if (isRegisterd){
            new Alert(Alert.AlertType.INFORMATION,"Registered Successful").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Registered Failed").show();
        }
    }

}
