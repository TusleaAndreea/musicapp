package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.embeddableIds.MusikArtSingerId;
import com.andreeatuslea.musicapp.tables.MusikArtSinger;
import org.springframework.data.repository.CrudRepository;

public interface MusikArtSingerRepo extends CrudRepository<MusikArtSinger, MusikArtSingerId> {
}
