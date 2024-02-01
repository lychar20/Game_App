package fr.charly.cap_entrprise.controller;

import fr.charly.cap_entrprise.DTO.RegisterPostDTO;
import fr.charly.cap_entrprise.mapping.UrlRoute;
import fr.charly.cap_entrprise.service.ReviewService;
import fr.charly.cap_entrprise.service.UserService;
import fr.charly.cap_entrprise.utils.ExcelReviewService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import fr.charly.cap_entrprise.utils.FlashMessage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController {

private ReviewService reviewService;

private UserService userService;

private ExcelReviewService excelService;

    @GetMapping
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            @PageableDefault(
                    size = 6,
                    sort = { "createdAt"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        mav.addObject("pageReviews", reviewService.getPageReviewByNickname(principal.getName(), pageable));
        mav.addObject("flashMessage", flashMessage);
        mav.setViewName("index");
        return mav;
    }


    @GetMapping(value = UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, String error) {
        if (error != null) {
            mav.addObject("error", "Identifiants sont incorrects !");
        }
        mav.setViewName("security/login");
        return mav;
    }

    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userForm", new RegisterPostDTO());
        return mav;
    }


    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(
            @ModelAttribute("userForm") RegisterPostDTO userForm,
            BindingResult bindingResult,
            ModelAndView mav
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userService.create(userForm);
        mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
        return mav;
    }


    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response) {
        try {
            File file = excelService.writeExcel();
            ByteArrayInputStream excelToByte = new ByteArrayInputStream(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath()))
            );
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(excelToByte, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
