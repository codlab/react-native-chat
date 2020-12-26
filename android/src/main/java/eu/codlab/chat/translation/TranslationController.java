package eu.codlab.chat.translation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class TranslationController {

    public static final TranslationController instance = new TranslationController();

    @NonNull
    private final HashMap<String, String> translations;

    private TranslationController() {
        translations = new HashMap<>();
    }

    public void set(@NonNull String key, @NonNull String translation) {
        translations.put(key, translation);
    }

    @Nullable
    public String get(@NonNull String key) {
        if (translations.containsKey(key)) {
            return translations.get(key);
        }
        return null;
    }
}
