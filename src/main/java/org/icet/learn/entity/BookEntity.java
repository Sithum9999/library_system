package org.icet.learn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BookEntity {

    private Integer isbn;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

}
