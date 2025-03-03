package org.icet.learn.controller.book;

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
import org.icet.learn.dto.Book;
import org.icet.learn.service.custom.BookService;

import java.net.URL;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colAvailability_status;

    @FXML
    private TableColumn colGenre;

    @FXML
    private TableColumn colIsbn;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tblBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtISbn;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtTitle;

    @Inject
    private BookService service;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Book book = new Book(null,txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), txtStatus.getText());
        boolean isBookAdded = service.addBook(book);
        if (isBookAdded){
            new Alert(Alert.AlertType.INFORMATION,"Book Added Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Added Failed").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isBookDeleted = service.deleteBook(txtISbn.getText());
        if (isBookDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Book Deleted Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Deleted Failed").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Book book = new Book(Integer.parseInt(txtISbn.getText()),txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), txtStatus.getText());
        boolean isUpdated = service.updateBook(book);
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Book Updated Successful").show();
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Updated Failed").show();
        }
    }

    @FXML
    void clickedBooks(MouseEvent event) {
        setValue((Book) tblBook.getSelectionModel().getSelectedItem());
    }


    void setValue(Book book){
        txtISbn.setText(book.getIsbn().toString());
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtGenre.setText(book.getGenre());
        txtStatus.setText(String.valueOf(book.getAvailabilityStatus()));
    }

    void setTextClear(){
        txtISbn.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtStatus.setText("");
    }

    private void LoadTable(){
        ObservableList<Book> allBook = service.getAllBook();
        tblBook.setItems(allBook);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability_status.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        LoadTable();
    }
}
