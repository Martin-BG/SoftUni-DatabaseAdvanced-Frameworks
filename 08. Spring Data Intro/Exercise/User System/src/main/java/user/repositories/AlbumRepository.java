package user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.models.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
