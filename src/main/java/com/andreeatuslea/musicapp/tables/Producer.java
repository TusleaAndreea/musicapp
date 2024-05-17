package com.andreeatuslea.musicapp.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class Producer {

    String name;
    String vorname;
    @Id
    Integer producerId;
    Date geburtsDatum;
    Integer labelId;

}
