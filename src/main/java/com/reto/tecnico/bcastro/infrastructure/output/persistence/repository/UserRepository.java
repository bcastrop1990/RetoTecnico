package com.reto.tecnico.bcastro.infrastructure.output.persistence.repository;

import com.reto.tecnico.bcastro.infrastructure.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
