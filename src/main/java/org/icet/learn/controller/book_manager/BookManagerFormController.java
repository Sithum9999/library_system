package org.icet.learn.controller.book_manager;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.icet.learn.controller.book.BookFormController;
import org.icet.learn.dto.Book;
import org.icet.learn.dto.BookManage;
import org.icet.learn.dto.User;
import org.icet.learn.service.custom.BookService;
import org.icet.learn.service.custom.BorrowBookService;
import org.icet.learn.service.custom.ReturnBookService;
import org.icet.learn.service.custom.UserService;
import org.icet.learn.util.db.DBConnection;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookManagerFormController implements Initializable {


    @Inject
    private UserService userService;

    @Inject
    private BookService bookService;

    @Inject
    private BorrowBookService borrowBookService;

    @Inject
    private ReturnBookService returnBookService;

    @FXML
    private TableColumn colOrderId;

    @FXML
    private TableColumn colBookTitle;

    @FXML
    private TableColumn colBorrowedDate;

    @FXML
    private TableColumn colDueDate;

    @FXML
    private TableColumn colFine;

    @FXML
    private TableColumn colMemberName;

    @FXML
    private TableColumn colReturnDate;

    @FXML
    private TableColumn colStatus;

    @FXML
    private JFXComboBox comboAllStatus;

    @FXML
    private JFXComboBox comboBookTitle;

    @FXML
    private JFXComboBox comboBookTitleRe;

    @FXML
    private JFXComboBox comboMemberName;

    @FXML
    private JFXComboBox comboMemberNameRe;

    @FXML
    private JFXComboBox comboSearchByMember;

    @FXML
    private DatePicker dateDueDate;

    @FXML
    private Label lblActiveBook;

    @FXML
    private Label lblBookBorrowed;

    @FXML
    private Label lblBookReturn;

    @FXML
    private Label lblBorrowedDate;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblOverdueBook;

    @FXML
    private TableView tblBookRecord;

    @FXML
    private JFXTextField txtOrdeID;

    @FXML
    private JFXTextField txtOverdueDays;

    @FXML
    private JFXTextField txtUpdatedFine;

    private int fineCalculate;

    private int upadtedFineFee;

    @FXML
    void btnAllAvailibleBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        BookManage borrowBooks = new BookManage(null,comboMemberName.getSelectionModel().getSelectedItem().toString(),comboBookTitle.getSelectionModel().getSelectedItem().toString(),dateDueDate.getValue().toString(),currentDate.toString(),null,"Borrowed",null);
        boolean isBorrowed = borrowBookService.borrowBook(borrowBooks);
        if (isBorrowed){
            new Alert(Alert.AlertType.INFORMATION,"Book Borrowed Successful").show();
            bookService.updateStatus(borrowBooks,"Borrowed");
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"The member borrowed limit is over than 3 or the book is already borrowed").show();
        }
    }


    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        fineCalculate = Integer.parseInt(txtOverdueDays.getText()) * 10;
        upadtedFineFee = Integer.parseInt(txtUpdatedFine.getText());
        fineCalculate = fineCalculate - upadtedFineFee;
        System.out.println(fineCalculate);

        BookManage returnBooks = new BookManage(Integer.parseInt(txtOrdeID.getText()),comboMemberNameRe.getSelectionModel().getSelectedItem().toString(),comboBookTitleRe.getSelectionModel().getSelectedItem().toString(),lblDueDate.getText(),lblBorrowedDate.getText(),currentDate.toString(),"Availible", String.valueOf(fineCalculate));
        boolean isReturned = returnBookService.returnBook(returnBooks);
        if (isReturned){
            new Alert(Alert.AlertType.INFORMATION,"Book Returned Successful").show();
            bookService.updateStatus(returnBooks,"Available");
            LoadTable();
            setTextClear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Returned Failed").show();
        }
    }

    @FXML
    void selectReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void selectReturnMemberNameOnAction(ActionEvent event) {

    }

    private void LoadTable(){
        ObservableList<BookManage> allHistory = borrowBookService.getAllHistory();
        tblBookRecord.setItems(allHistory);
    }

    void LoadUsers(){
        ObservableList<User> allUser = userService.getAllUser();
        ObservableList<String> allUsersName = FXCollections.observableArrayList();
        allUser.forEach(user -> {
           allUsersName.add(user.getName());
        });
        comboMemberName.setItems(allUsersName);
        comboMemberNameRe.setItems(allUsersName);
    }

    void LoadBooks(){
        ObservableList<Book> allBook = bookService.getAllBook();
        ObservableList<String> allBooksName = FXCollections.observableArrayList();
        allBook.forEach(book -> {
            allBooksName.add(book.getTitle());
        });
        comboBookTitle.setItems(allBooksName);
        comboBookTitleRe.setItems(allBooksName);
    }

    public void clickedBooksDetails(MouseEvent mouseEvent) {
        setValue((BookManage) tblBookRecord.getSelectionModel().getSelectedItem());
    }

    void setValue(BookManage book){
        System.out.println(book);
        comboMemberNameRe.setValue(book.getMember());
        comboBookTitleRe.setValue(book.getTitle());
        txtOrdeID.setText(book.getId().toString());
        lblBorrowedDate.setText(book.getBorrowDate());
        lblDueDate.setText(book.getDueDate());
    }

    void setTextClear(){
        txtOrdeID.setText("");
        lblDueDate.setText("");
        lblBorrowedDate.setText("");
        txtOverdueDays.setText("");
        comboMemberNameRe.setValue("");
        comboBookTitleRe.setValue("");
        comboMemberName.setValue("");
        comboBookTitle.setValue("");
        dateDueDate.setValue(null);
    }

    @FXML
    void btnAllAvailableBooksOnAction(ActionEvent event) {
        try {
            JasperDesign load = JRXmlLoader.load("src/main/resources/report/availableBook.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "availableBooks.pdf");
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAllBorrowedBooksOnAction(ActionEvent event) {
        try {
            JasperDesign load = JRXmlLoader.load("src/main/resources/report/borrowedBook.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "borrowedBooks.pdf");
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAllOverdueBooksOnAction(ActionEvent event) {
        try {
            JasperDesign load = JRXmlLoader.load("src/main/resources/report/overdueBooks.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "OverdueBooks.pdf");
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("member"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        LoadTable();
        LoadUsers();
        LoadBooks();
    }

}
