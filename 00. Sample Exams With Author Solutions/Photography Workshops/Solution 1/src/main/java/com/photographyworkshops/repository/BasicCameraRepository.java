package com.photographyworkshops.repository;

import com.photographyworkshops.domain.entities.cameras.BasicCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicCameraRepository extends JpaRepository<BasicCamera, Long> {
}
