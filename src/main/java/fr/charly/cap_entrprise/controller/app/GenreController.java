package fr.charly.cap_entrprise.controller.app;

import fr.charly.cap_entrprise.DTO.GameDTO;
import fr.charly.cap_entrprise.DTO.GenreDTO;
import fr.charly.cap_entrprise.entity.User;
import fr.charly.cap_entrprise.mapping.UrlRoute;
import fr.charly.cap_entrprise.service.GameService;
import fr.charly.cap_entrprise.service.GenreService;
import fr.charly.cap_entrprise.service.ReviewService;
import fr.charly.cap_entrprise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GameService gameService;





//    @GetMapping(UrlRoute.URL_GAME_GENRE)
//    public ModelAndView show(
//            ModelAndView mav,
//            //@PathVariable String slug,
//            @PageableDefault(
//                    size = 6, // nb Element par page
//                    sort = { "createdAt" }, // order by
//                    direction = Sort.Direction.DESC
//            ) Pageable pageable,
//            Principal principal
//
//    ) {
//        mav.setViewName("genre/show");
//        mav.addObject("gameDTO", new GameDTO());
//        mav.addObject("genre", new GenreDTO());
//        mav.addObject("allGenre", genreService.findAll() );
//        return mav;
//    }





    @GetMapping(path = UrlRoute.URL_GAME_GENRE )
    public ModelAndView show(ModelAndView mav){
        mav.setViewName("genre/show");
        mav.addObject("genre", genreService.findAllSorted());
        mav.addObject("game", gameService.findTop6ByOrderByGenre());
        //mav.addObject("reviews", reviewService.findTop5ByCreatedAtDesc());

        return mav;
    }



}

