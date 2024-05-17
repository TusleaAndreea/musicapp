package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.embeddableIds.ErfolgId;
import com.andreeatuslea.musicapp.tables.Erfolg;
import org.springframework.data.repository.CrudRepository;

public interface ErfolgRepo extends CrudRepository<Erfolg, ErfolgId> {
}
