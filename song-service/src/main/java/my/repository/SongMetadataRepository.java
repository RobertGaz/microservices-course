package my.repository;

import my.model.db.SongMetadataTableObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongMetadataRepository extends JpaRepository<SongMetadataTableObject, Integer> {

}
