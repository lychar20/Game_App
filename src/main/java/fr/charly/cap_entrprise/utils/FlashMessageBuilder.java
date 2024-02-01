package fr.charly.cap_entrprise.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class FlashMessageBuilder {

    public FlashMessage createSuccessFlashMessage(String message) {
        return new FlashMessage("success", message);
    }

    public FlashMessage createDangerFlashMessage(String message) {
        return new FlashMessage("danger", message);
    }

    public FlashMessage createWarningFlashMessage(String message) {
        return new FlashMessage("warning", message);
    }

    public FlashMessage createInfosFlashMessage(String message) {
        return new FlashMessage("infos", message);
    }

}
