
package fr.charly.cap_entrprise.repository;

import fr.charly.cap_entrprise.entity.Classification;
import fr.charly.cap_entrprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>, EntityNomenclatureRepository<Classification> {



}
