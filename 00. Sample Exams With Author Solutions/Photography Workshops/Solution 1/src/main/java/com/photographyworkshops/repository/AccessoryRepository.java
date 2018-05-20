package com.photographyworkshops.repository;

import com.photographyworkshops.domain.entities.accessories.Accessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory, Long> {
}
