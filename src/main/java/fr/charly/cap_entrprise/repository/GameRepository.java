
package fr.charly.cap_entrprise.repository;

import fr.charly.cap_entrprise.entity.Game;
import fr.charly.cap_entrprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, EntityNomenclatureRepository<Game> {

    List<Game> findTop9ByOrderByPublishedAtDesc();

    List<Game> findTop6ByOrderByPublishedAtDesc();

    List<Game> findTop6ByOrderByGenre();

}
