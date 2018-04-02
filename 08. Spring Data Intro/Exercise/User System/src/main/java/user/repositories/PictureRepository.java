package user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.models.entities.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
