package fr.charly.cap_entrprise.controller.app;

import fr.charly.cap_entrprise.mapping.UrlRoute;
import fr.charly.cap_entrprise.service.GameService;
import fr.charly.cap_entrprise.service.ReviewService;
import fr.charly.cap_entrprise.service.UserService;
import fr.charly.cap_entrprise.utils.FlashMessage;
import fr.charly.cap_entrprise.utils.FlashMessageBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private FlashMessageBuilder flashMessageBuilder;

    private final UserService userService;



    //@GetMapping(path = "/game")
    @GetMapping(path = UrlRoute.URL_REVIEW + "/{id}", name = "id")
    public ModelAndView review(
            @PathVariable Long id,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("index");
        }

        mav.setViewName("review");
        mav.addObject("review", reviewService.findById(id));
        //mav.addObject("reviews", reviewService.findTop5ByCreatedAtDesc());

        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_MODERATE)
    public ModelAndView moderate(
            @PathVariable Long id,
            @PathVariable Long moderate,
            ModelAndView modelAndView,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        boolean isModerate = reviewService.moderateReview(principal.getName(), id, moderate);
        FlashMessage flashMessage = flashMessageBuilder.createSuccessFlashMessage("Le commentaire a bien été modéré !");
        if (!isModerate) {
            flashMessage = flashMessageBuilder.createWarningFlashMessage("Le commentaire a bien été supprimé !");
        }
        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


}

