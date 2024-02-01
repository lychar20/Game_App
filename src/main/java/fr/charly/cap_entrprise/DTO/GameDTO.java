package fr.charly.cap_entrprise.DTO;

import fr.charly.cap_entrprise.entity.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {


    @NotBlank
    private String description;

    @NotBlank
    private String publishedAt;

    @NotBlank
    private String name;

    @NotEmpty
    private List<Platform> platforms;

    @NotNull
    private Classification classification;

    @NotNull
    private Genre genre;

    @NotNull
    private BusinessModel businessModel;

    @NotNull
    private Publisher publisher;

}
