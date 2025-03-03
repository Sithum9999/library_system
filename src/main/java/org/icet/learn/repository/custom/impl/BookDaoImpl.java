package org.icet.learn.repository.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.icet.learn.entity.BookEntity;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.BookDao;
import org.icet.learn.util.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean save(BookEntity entity) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into books (title, author, genre, availability_status) values (?,?,?,?)");
            stm.setObject(1,entity.getTitle());
            stm.setObject(2,entity.getAuthor());
            stm.setObject(3,entity.getGenre());
            stm.setObject(4,entity.getAvailabilityStatus());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("delete from books where isbn = ?");
            stm.setObject(1,id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(BookEntity entity) {
        System.out.println(entity);
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE books SET title = ?, author = ?, genre = ?, availability_status = ? WHERE isbn = ?");
            stm.setObject(1,entity.getTitle());
            stm.setObject(2,entity.getAuthor());
            stm.setObject(3,entity.getGenre());
            stm.setObject(4,entity.getAvailabilityStatus());
            stm.setObject(5,entity.getIsbn());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<BookEntity> getAll() {
        ObservableList<BookEntity> observableArrayList = FXCollections.observableArrayList();
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from books");
            while(res.next()){
                observableArrayList.add(new BookEntity(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)));
            }
            return observableArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateStatus(BookManageEntity book, String status) {
        System.out.println(status);
        System.out.println(book.getTitle());
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE books SET availability_status = ? WHERE title = ?");
            stm.setObject(1, status);
            stm.setObject(2, book.getTitle());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
