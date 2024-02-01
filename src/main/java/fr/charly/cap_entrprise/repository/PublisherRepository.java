
package fr.charly.cap_entrprise.repository;

import fr.charly.cap_entrprise.entity.Publisher;
import fr.charly.cap_entrprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>, EntityNomenclatureRepository<Publisher> {



}
