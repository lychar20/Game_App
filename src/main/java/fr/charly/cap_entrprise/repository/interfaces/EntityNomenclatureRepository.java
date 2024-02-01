
package fr.charly.cap_entrprise.repository.interfaces;

import fr.charly.cap_entrprise.entity.BusinessModel;
import fr.charly.cap_entrprise.entity.Game;

import java.util.List;
import java.util.Optional;

public interface EntityNomenclatureRepository<T> {

    Optional<T> findByName(String name);

    Optional<T> findBySlug(String slug);


    List<T> findAllByOrderByNameAsc();
}
