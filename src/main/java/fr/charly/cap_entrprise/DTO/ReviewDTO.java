package fr.charly.cap_entrprise.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {


    @NotBlank
    private String description;

    @DecimalMin(
            value = "0",
            message= "The rating should be at least 0"
    )
    @DecimalMax(
            value = "20",
            message= "The rating should be 20 at max"
    )
    @NotNull
    private float rating;

}
