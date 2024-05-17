package com.andreeatuslea.musicapp.repositories;

import com.andreeatuslea.musicapp.tables.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepo extends CrudRepository<Album, Integer> {

    Album findAlbumByAlbumId(Integer albumId);

}
