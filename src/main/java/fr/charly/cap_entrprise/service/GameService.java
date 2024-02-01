package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.DTO.GameDTO;
import fr.charly.cap_entrprise.entity.Game;
import fr.charly.cap_entrprise.entity.Moderator;
import fr.charly.cap_entrprise.repository.GameRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOFindByIdInterface;
import fr.charly.cap_entrprise.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService implements DAOFindByIdInterface<Game>, DAOFindBySlugInterface<Game> {

    private GameRepository gameRepository;

    private UserService userService;


    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Game> findTop6ByOrderByPublishedAtDesc() {
        return gameRepository.findTop6ByOrderByPublishedAtDesc();
    }

    public List<Game> findTop6ByOrderByGenre() {return gameRepository.findTop6ByOrderByGenre();}

    @Override
    public Game findBySlug(String slug) {
        return gameRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Game create(GameDTO gameDTO, String nickname) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setPublishedAt(LocalDate.parse(gameDTO.getPublishedAt()));
        game.setGenre(gameDTO.getGenre());
        game.setBusinessModel(gameDTO.getBusinessModel());
        game.setPublisher(gameDTO.getPublisher());
        game.setClassification(gameDTO.getClassification());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setModerator((Moderator) userService.findByNickname(nickname));
        game.setImage("https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg");
        return gameRepository.saveAndFlush(game);
    }


    public void saveImageToGame(String fileName, String slug) {
        Game game = findBySlug(slug);
        game.setImage(fileName);
        gameRepository.flush();
    }
}
