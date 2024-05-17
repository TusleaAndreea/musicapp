package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Preis;
import org.springframework.data.repository.CrudRepository;

public interface PreisRepo extends CrudRepository<Preis, Integer> {

    Preis findByPreisId(Integer preisId);

}
