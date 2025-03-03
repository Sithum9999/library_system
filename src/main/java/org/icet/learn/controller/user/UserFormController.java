package org.icet.learn.controller.user;

import com.google.inject.Inject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.icet.learn.dto.User;
import org.icet.learn.service.custom.UserService;
import org.icet.learn.util.db.DBConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private TableColumn colContactNumber;

    @FXML
    private TableColumn colMembershipDate;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colUserId;

    @FXML
    private TableView tblUser;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtMembershipDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserID;

    @Inject
    private UserService service;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        User user = new User(null,txtName.getText(), txtMembershipDate.getText(), txtContactNumber.getText());
        boolean isUserAdded = service.addUser(user);
        if (isUserAdded){
            new Alert(Alert.AlertType.INFORMATION,"User Added Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"User Added Failed").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isUserDeleted = service.deleteUser(txtUserID.getText());
        if (isUserDeleted){
            new Alert(Alert.AlertType.INFORMATION,"User Deleted Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"User Deleted Failed").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        User user = new User(Integer.parseInt(txtUserID.getText()),txtName.getText(), txtMembershipDate.getText(), txtContactNumber.getText());
        boolean isUpdated = service.updateUser(user);
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"User Updated Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"User Updated Failed").show();
        }

    }

    public void clickedUsers(MouseEvent mouseEvent) {
        setValue((User) tblUser.getSelectionModel().getSelectedItem());
    }

    void setValue(User user){
        txtUserID.setText(user.getId().toString());
        txtName.setText(user.getName());
        txtMembershipDate.setText(user.getMembershipDate());
        txtContactNumber.setText(user.getNumber());
    }

    void setTextClear(){
        txtUserID.setText("");
        txtName.setText("");
        txtMembershipDate.setText("");
        txtContactNumber.setText("");
    }

    private void LoadTable(){
        ObservableList<User> allUsers = service.getAllUser();
        tblUser.setItems(allUsers);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMembershipDate.setCellValueFactory(new PropertyValueFactory<>("membershipDate"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        LoadTable();
    }
}
