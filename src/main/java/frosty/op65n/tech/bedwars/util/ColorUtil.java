package frosty.op65n.tech.bedwars.util;

import me.mattstudios.mfmsg.bukkit.BukkitMessage;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.stream.Collectors;

public final class ColorUtil {

    private static final BukkitMessage BUKKIT_MESSAGE = BukkitMessage.create();

    /**
     * Translates the given text and returns a component from it
     *
     * @param text input text to translate
     * @return Component from the translated text
     */
    public static Component translate(final String text) {
        return Component.text(translateLegacy(text));
    }

    /**
     * Translates the given list and returns a list of components
     *
     * @param text list to translate
     * @return List of translated components
     */
    public static List<Component> translate(final List<String> text) {
        return text.stream().map(ColorUtil::translate).collect(Collectors.toList());
    }

    /**
     * Translates the given text and returns it as a string
     *
     * @param text input text to translate
     * @return translated string
     */
    public static String translateLegacy(final String text) {
        return BUKKIT_MESSAGE.parse(text).toString();
    }

    /**
     * Translates the given list and returns a list of strings
     *
     * @param text input list to translate
     * @return list of translated string
     */
    public static List<String> translateLegacy(final List<String> text) {
        return text.stream().map(ColorUtil::translateLegacy).collect(Collectors.toList());
    }

}
