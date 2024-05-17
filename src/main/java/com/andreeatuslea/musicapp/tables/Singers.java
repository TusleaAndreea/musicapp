package com.andreeatuslea.musicapp.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class Singers {

    String name;
    String vorname;
    @Id
    Integer singerId;
    Date geburtsDatum;
    Integer labelId;
    String nickname;

}
