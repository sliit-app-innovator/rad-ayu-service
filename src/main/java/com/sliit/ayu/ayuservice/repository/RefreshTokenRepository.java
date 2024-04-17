package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
}
