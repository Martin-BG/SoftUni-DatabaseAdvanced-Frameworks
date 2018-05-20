package com.photographyworkshops.repository;

import com.photographyworkshops.domain.entities.lens.Lens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LenRepository extends CrudRepository<Lens, Long> {

    Set<Lens> findByIdIn(List<Long> ids);
}
