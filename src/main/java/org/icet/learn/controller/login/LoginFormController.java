package org.icet.learn.controller.login;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.icet.learn.dto.Login;
import org.icet.learn.service.custom.LoginService;
import org.icet.learn.util.AppModule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private JFXButton btnLogin, btnRegister;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @Inject
    private LoginService service;



    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Login login = new Login(txtEmail.getText(),txtPassword.getText());
        boolean isLoginAdded = service.searchLogin(login);
        if (isLoginAdded){
            new Alert(Alert.AlertType.INFORMATION,"Login Successful").show();
            Injector injector = Guice.createInjector(new AppModule());

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml")); // Corrected resource path
            loader.setControllerFactory(injector::getInstance);

            stage.setScene(new Scene(loader.load()));
            stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Login Failed").show();
        }
    }

    @FXML
    void btnRegisterdOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register.fxml")); // Corrected resource path
        stage.setTitle("Register Form");
        loader.setControllerFactory(injector::getInstance);

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("-fx-background-color: #1C1C1C; -fx-background-radius: 20px;"));
        btnLogin.setOnMouseExited(e -> btnLogin.setStyle("-fx-background-color: #203A43; -fx-background-radius: 20px;"));

        btnRegister.setOnMouseEntered(e -> btnRegister.setStyle("-fx-background-color: #1C1C1C; -fx-background-radius: 20px;"));
        btnRegister.setOnMouseExited(e -> btnRegister.setStyle("-fx-background-color: #2C5364; -fx-background-radius: 20px;"));
    }
}
