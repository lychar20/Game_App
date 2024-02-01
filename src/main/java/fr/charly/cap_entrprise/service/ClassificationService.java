package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.entity.Classification;
import fr.charly.cap_entrprise.repository.ClassificationRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOEntityIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassificationService implements DAOEntityIdInterface<Classification> {

    private ClassificationRepository classificationRepository;
    @Override
    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    @Override
    public List<Classification> findAllSorted() {
        return classificationRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Classification findById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
