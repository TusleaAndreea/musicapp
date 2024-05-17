package com.andreeatuslea.musicapp.embeddableIds;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class LiedInTopId implements Serializable {

    Integer topId;
    Integer liedId;

}
