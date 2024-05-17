package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Label;
import org.springframework.data.repository.CrudRepository;

public interface LabelRepo extends CrudRepository<Label, Integer> {
}
