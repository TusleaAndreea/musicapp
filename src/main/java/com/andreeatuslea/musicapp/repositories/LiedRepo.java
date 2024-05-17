package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Lied;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LiedRepo extends CrudRepository<Lied, Integer> {

    Lied findLiedByLiedId(Integer liedId);

    List<Lied> findLiedsByDauerIsGreaterThan(Integer value);

    List<Lied> findAllByLiedIdIsGreaterThan(Integer value);

    List<Lied> findAllByAlbumId(Integer albumId);

}
