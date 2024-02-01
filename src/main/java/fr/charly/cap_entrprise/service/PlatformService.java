package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.entity.Platform;
import fr.charly.cap_entrprise.repository.PlatformRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOEntityIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService implements DAOEntityIdInterface<Platform> {

    private PlatformRepository platformRepository;
    @Override
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public List<Platform> findAllSorted() {
        return platformRepository.findAllByOrderByNameAsc();
    }


    @Override
    public Platform findById(Long id) {
        return platformRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }




}
