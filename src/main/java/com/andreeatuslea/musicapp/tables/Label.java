package com.andreeatuslea.musicapp.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class Label {

    String name;
    @Id
    Integer labelId;
    Date datum;

}
