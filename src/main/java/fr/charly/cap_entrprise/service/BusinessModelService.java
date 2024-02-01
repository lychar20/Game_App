package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.entity.BusinessModel;
import fr.charly.cap_entrprise.repository.BusinessModelRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOEntityIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusinessModelService implements DAOEntityIdInterface<BusinessModel> {


    private BusinessModelRepository businessModelRepository;


    @Override
    public List<BusinessModel> findAll()  {
        return businessModelRepository.findAll();
    }

    @Override
    public List<BusinessModel> findAllSorted() {
        return businessModelRepository.findAllByOrderByNameAsc();
    }

    @Override
    public BusinessModel findById(Long id) {
        return businessModelRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }




}
