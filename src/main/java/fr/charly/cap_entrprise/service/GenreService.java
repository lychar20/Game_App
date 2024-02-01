package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.entity.Game;
import fr.charly.cap_entrprise.entity.Genre;
import fr.charly.cap_entrprise.repository.GameRepository;
import fr.charly.cap_entrprise.repository.GenreRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOEntityIdInterface;
import fr.charly.cap_entrprise.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService implements DAOEntityIdInterface<Genre>, DAOFindBySlugInterface<Genre> {

    private GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findAllSorted() {
        return genreRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Genre findBySlug(String slug) {
        return genreRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }
}
