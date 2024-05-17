package com.andreeatuslea.musicapp.embeddableIds;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class MusikArtSingerId implements Serializable {

    Integer singerId;
    Integer artId;

}
