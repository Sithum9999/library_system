package org.icet.learn.repository.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.BorrowBookDao;
import org.icet.learn.util.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowBookDaoImpl implements BorrowBookDao {
    @Override
    public ObservableList<BookManageEntity> getAllHistory() {
        ObservableList<BookManageEntity> observableArrayList = FXCollections.observableArrayList();
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from borrow_books");
            while(res.next()){
                observableArrayList.add(new BookManageEntity(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)));
            }
            return observableArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean borrowBook(BookManageEntity entity) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO borrow_books (member, title, due_date, borrow_date, status) " + "SELECT ?, ?, ?, ?, 'Borrowed' " + "WHERE (SELECT COUNT(*) FROM borrow_books WHERE member = ? AND status = 'Borrowed') < 3 " + "AND (SELECT COUNT(*) FROM borrow_books WHERE title = ? AND status = 'Borrowed') = 0");
            stm.setObject(1, entity.getMember());
            stm.setObject(2, entity.getTitle());
            stm.setObject(3, entity.getDueDate());
            stm.setObject(4, entity.getBorrowDate());
            stm.setObject(5, entity.getMember()); // Check member borrowed count > 3
            stm.setObject(6, entity.getTitle()); // Check the book is already borrowed

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
