package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.embeddableIds.LiedInTopId;
import com.andreeatuslea.musicapp.tables.LiedInTop;
import org.springframework.data.repository.CrudRepository;

public interface LiedInTopRepo extends CrudRepository<LiedInTop, LiedInTopId> {

    LiedInTop findLiedInTopByLiedInTopId_LiedIdAndLiedInTopId_LiedId(Integer topId, Integer liedId);

}
