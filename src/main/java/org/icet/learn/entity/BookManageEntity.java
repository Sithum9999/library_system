package org.icet.learn.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BookManageEntity {
    private Integer id;
    private String member;
    private String title;
    private String dueDate;
    private String borrowDate;
    private String returnDate;
    private String status;
    private String fine;
}
