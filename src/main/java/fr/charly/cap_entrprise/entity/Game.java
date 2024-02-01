package fr.charly.cap_entrprise.entity;

import fr.charly.cap_entrprise.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Game implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    @Column(nullable = false)
    private LocalDate publishedAt;


    private String image;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Classification classification;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusinessModel businessModel;

    private String slug;

    @ManyToMany
    private List<Platform> platforms = new ArrayList<>();

    @OneToMany(mappedBy = "game")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Moderator moderator;

    public void addPlatform(Platform platform) {
        if (getPlatforms().isEmpty()) {
            getPlatforms().add(platform);
        }
    }

    @Override
    public String getField() {
        return name;
    }
}