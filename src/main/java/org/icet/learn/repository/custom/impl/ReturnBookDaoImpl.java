package org.icet.learn.repository.custom.impl;

import org.icet.learn.dto.BookManage;
import org.icet.learn.entity.BookManageEntity;
import org.icet.learn.repository.custom.BorrowBookDao;
import org.icet.learn.repository.custom.ReturnBookDao;
import org.icet.learn.util.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnBookDaoImpl implements ReturnBookDao {

    @Override
    public boolean returnBook(BookManageEntity entity) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE borrow_books SET member = ?, title = ?, due_date = ?, borrow_date = ?, return_date = ?, status = ?, fine = ? WHERE id = ?");
            stm.setObject(1,entity.getMember());
            stm.setObject(2,entity.getTitle());
            stm.setObject(3,entity.getDueDate());
            stm.setObject(4,entity.getBorrowDate());
            stm.setObject(5,entity.getReturnDate());
            stm.setObject(6,entity.getStatus());
            stm.setObject(7,entity.getFine());
            stm.setObject(8,entity.getId());
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
