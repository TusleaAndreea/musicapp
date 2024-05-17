package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Singers;
import org.springframework.data.repository.CrudRepository;

public interface SingersRepo extends CrudRepository<Singers, Integer> {

    Singers getSingersBySingerId(Integer singerId);

}
