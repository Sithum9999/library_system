package org.icet.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookManage {
    private Integer id;
    private String member;
    private String title;
    private String dueDate;
    private String borrowDate;
    private String returnDate;
    private String status;
    private String fine;
}
