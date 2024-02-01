
package fr.charly.cap_entrprise.repository;

import fr.charly.cap_entrprise.entity.Game;
import fr.charly.cap_entrprise.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByGameAndModeratorIsNotNull(Game game, Pageable pageable);

    @Query("select r from Review r where r.moderatedAt is not null OR (r.gamer.nickname = ?1 and r.moderatedAt is null)")
    Page<Review> findByUserNickname(String nickname, Pageable pageable);


    Page<Review> findByModeratorIsNotNullOrGamerNickname(String nickname, Pageable pageable);

    Page<Review> findByModeratorIsNotNull(Pageable pageable);

    Page<Review> findByModeratorIsNull(Pageable pageable);

    Page<Review> findAllByGamerNickname(String nickname, Pageable pageable);

    Page<Review> findByModeratorIsNotNullAndGamerNickname(String nickname, Pageable pageable);

}
