
package fr.charly.cap_entrprise.repository;

import fr.charly.cap_entrprise.entity.BusinessModel;
import fr.charly.cap_entrprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessModelRepository extends JpaRepository<BusinessModel, Long>, EntityNomenclatureRepository<BusinessModel> {




}
