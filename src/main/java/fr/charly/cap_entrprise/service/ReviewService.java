package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.DTO.ReviewDTO;
import fr.charly.cap_entrprise.entity.*;
import fr.charly.cap_entrprise.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    private UserService userService;


    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }


    public List<Review> findAll() {
        return reviewRepository.findAll();
    }


    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Page<Review> findByUserNickname(String nickname, Pageable pageable) {
        return reviewRepository.findByModeratorIsNotNullOrGamerNickname(nickname, pageable);
    }

    public Page<Review> findAllByGame(Game game, Pageable pageable) {
        return reviewRepository.findAllByGameAndModeratorIsNotNull(game, pageable);
    }

    public Review createReview(ReviewDTO reviewDTO, Game game, String name) {
        Review review = new Review();
        review.setGame(game);
        review.setGamer((Gamer) userService.findByNickname(name));
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());
        return reviewRepository.saveAndFlush(review);
    }



    public boolean moderateReview(String nickname, Long id, Long moderate) {
        Review review = findById(id);
        boolean isModerate = true;
        if (moderate == 1L) {
            review.setModerator((Moderator) userService.findByNickname(nickname));
            review.setModeratedAt(LocalDateTime.now());
        } else {
            reviewRepository.delete(review);
            isModerate = false;
        }
        reviewRepository.flush();
        return isModerate;
    }


    public Page<Review> getPageReviewByNickname(String nickname, Pageable pageable) {
        User user = userService.findByNickname(nickname);
        Page<Review> pageReviews = findByUserNickname(nickname, pageable);
        if (user.isModerator()) {
            Sort.Order order = pageable.getSort().getOrderFor("moderator");
            if (order != null) {
                if (order.isAscending()) {
                    pageReviews = reviewRepository.findByModeratorIsNull(pageable);
                } else {
                    pageReviews = reviewRepository.findByModeratorIsNotNull(pageable);
                }
            } else {
                pageReviews = reviewRepository.findAll(pageable);
            }
        }
        return pageReviews;
    }


    public Page<Review> findReviewsForProfile(
            User user,
            Principal principal,
            Pageable pageable
    ) {
        if (user.getNickname().equals(principal.getName())) {
            return reviewRepository.findAllByGamerNickname(user.getNickname(), pageable);
        }
        return reviewRepository.findByModeratorIsNotNullAndGamerNickname(user.getNickname(), pageable);
    }

}
