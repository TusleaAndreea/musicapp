package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Producer;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepo extends CrudRepository<Producer, Integer> {

    Producer getProducerByProducerId(Integer producerId);

}
