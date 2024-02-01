package fr.charly.cap_entrprise.controller.app;

import fr.charly.cap_entrprise.entity.User;
import fr.charly.cap_entrprise.mapping.UrlRoute;
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
public class UserController {

    private final ReviewService reviewService;
    private final UserService userService;



    @GetMapping(UrlRoute.URL_USER_SHOW)
    public ModelAndView show(
            ModelAndView mav,
            @PathVariable String uuid,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            Principal principal

    ) {
        mav.setViewName("usershowview");
        User user = userService.findByUuid(uuid);
        mav.addObject("user", user);
        mav.addObject("pageReviews", reviewService.findReviewsForProfile(user, principal, pageable));
        return mav;
    }


}

