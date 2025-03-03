package org.icet.learn.entity;

import jakarta.persistence.*;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.icet.learn.dto.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserEntity {
    private int id;
    private String name;
    private String membershipDate;
    private String number;

}
