package com.andreeatuslea.musicapp.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Preis {

    String name;
    @Id
    Integer preisId;

}
