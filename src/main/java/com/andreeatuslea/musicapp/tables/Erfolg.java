package com.andreeatuslea.musicapp.tables;


import com.andreeatuslea.musicapp.embeddableIds.ErfolgId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Erfolg {

    @EmbeddedId
    ErfolgId id;

}
