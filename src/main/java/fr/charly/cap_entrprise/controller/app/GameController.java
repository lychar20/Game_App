package fr.charly.cap_entrprise.controller.app;

import fr.charly.cap_entrprise.DTO.GameDTO;
import fr.charly.cap_entrprise.DTO.ReviewDTO;
import fr.charly.cap_entrprise.entity.Game;
import fr.charly.cap_entrprise.mapping.UrlRoute;
import fr.charly.cap_entrprise.service.*;
import fr.charly.cap_entrprise.utils.FileUploadService;
import fr.charly.cap_entrprise.utils.FlashMessageBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppNewGame")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private ReviewService reviewService;
    private ClassificationService classificationService;
    private BusinessModelService businessModelService;
    private PublisherService publisherService;
    private GenreService genreService;
    private PlatformService platformService;
    private FlashMessageBuilder flashMessageBuilder;
    private FileUploadService fileUploadService;


//    @GetMapping(path = "/game")
    @GetMapping(path = UrlRoute.URL_GAME )
    public ModelAndView show(ModelAndView mav){
        mav.setViewName("game/index");
        mav.addObject("games", gameService.findTop6ByOrderByPublishedAtDesc());
        //mav.addObject("reviews", reviewService.findTop5ByCreatedAtDesc());

        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME_SLUG)
    public ModelAndView vue(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("reviewFormMessage") String reviewFormMessage,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        mav.setViewName("game/show");  //avant c'était "vue"
        if (principal != null) {
            mav.addObject("reviewDTO", new ReviewDTO());
        }

        Game game = gameService.findBySlug(slug);
        mav.addObject("formMessage", reviewFormMessage);
        mav.addObject("game", game);
        mav.addObject("pageReviews", reviewService.findAllByGame(game, pageable));
        return mav;
    }


    @PostMapping(UrlRoute.URL_GAME_SLUG)
    public ModelAndView show( //"vue" avant mais wrong
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("reviewDto") ReviewDTO reviewDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("game/show"); //"vue" avant mais wrong
            return mav;
        }
        reviewService.createReview(
                reviewDTO,
                gameService.findBySlug(slug),
                principal.getName()
        );
        redirectAttributes.addFlashAttribute("reviewFormMessage", "Votre commentaire a bien été enregistré, il est actuellement en attente de modération !");
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);
        return mav;
    }


    @GetMapping(UrlRoute.URL_GAME_NEW)
    public ModelAndView create(ModelAndView mav) {
        mav.setViewName("game/new");
        mav.addObject("gameDto", new GameDTO());
        mav.addObject("genres", genreService.findAllSorted());
        mav.addObject("classifications", classificationService.findAllSorted());
        mav.addObject("businessModels", businessModelService.findAllSorted());
        mav.addObject("publishers", publisherService.findAllSorted());
        mav.addObject("platforms", platformService.findAllSorted());
        return mav;
    }

    @PostMapping(UrlRoute.URL_GAME_NEW)
    public ModelAndView create(
            ModelAndView mav,
            @ModelAttribute("gameDto") GameDTO gameDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("game/new");
            return mav;
        }

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                flashMessageBuilder.createSuccessFlashMessage("Jeu créé avec succès !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + gameService.create(gameDTO, principal.getName()).getSlug());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_GAME_UPLOAD_IMAGE_PATH)
    public ModelAndView uploadImage(
            ModelAndView mav,
            @PathVariable String slug
    ) {
        mav.setViewName("game/upload-image");
        return mav;
    }

    @PostMapping(value = UrlRoute.URL_GAME_UPLOAD_IMAGE_PATH)
    public ModelAndView uploadImage(
            ModelAndView mav,
            @RequestParam("file") MultipartFile file,
            @PathVariable String slug,
            RedirectAttributes redirectAttributes
    ) {
        String fileName = fileUploadService.uploadFile(file, "game", slug);
        if (fileName.contains("erreur")) {
            redirectAttributes.addFlashAttribute(
                    "flashMessage",
                    flashMessageBuilder.createDangerFlashMessage(fileName)
            );
            mav.setViewName("game/upload-image");
            return mav;
        }
        gameService.saveImageToGame(fileName, slug);
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                flashMessageBuilder.createSuccessFlashMessage("Image téléversée avec succès !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);
        return mav;
    }

}
