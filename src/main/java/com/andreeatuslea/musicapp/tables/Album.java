package com.andreeatuslea.musicapp.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class Album {

    @Id
    Integer albumId;

    Integer nrCopiesSold;

    Date veroeffentlichung;

    Integer singerId;

    String name;

}
