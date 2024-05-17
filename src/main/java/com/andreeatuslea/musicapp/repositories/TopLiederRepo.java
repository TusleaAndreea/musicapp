package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.TopLieder;
import org.springframework.data.repository.CrudRepository;

public interface TopLiederRepo extends CrudRepository<TopLieder, Integer> {

    TopLieder getTopLiederByTopId(Integer topId);

}
