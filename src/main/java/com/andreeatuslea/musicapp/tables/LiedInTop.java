package com.andreeatuslea.musicapp.tables;

import com.andreeatuslea.musicapp.embeddableIds.LiedInTopId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LiedInTop {

    @EmbeddedId
    LiedInTopId liedInTopId;
    Integer position;

}
