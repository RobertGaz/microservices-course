package my.repository;

import my.model.db.SongTableObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongTableObject, Integer> {

}
