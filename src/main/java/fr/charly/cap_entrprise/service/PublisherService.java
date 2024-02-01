package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.entity.Publisher;
import fr.charly.cap_entrprise.repository.PublisherRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOEntityIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService implements DAOEntityIdInterface<Publisher> {

    private PublisherRepository publisherRepository;
    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Publisher> findAllSorted() {
        return publisherRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


}
