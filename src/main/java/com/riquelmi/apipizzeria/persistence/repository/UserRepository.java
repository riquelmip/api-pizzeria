package com.riquelmi.apipizzeria.persistence.repository;

import com.riquelmi.apipizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
