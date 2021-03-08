package frosty.op65n.tech.bedwars.util;

import me.mattstudios.mfmsg.bukkit.BukkitMessage;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.stream.Collectors;

public final class ColorUtil {

    private static final BukkitMessage BUKKIT_MESSAGE = BukkitMessage.create();

    public static Component translate(final String text) {
        return Component.text(translateLegacy(text));
    }

    public static List<Component> translate(final List<String> text) {
        return text.stream().map(ColorUtil::translate).collect(Collectors.toList());
    }

    public static String translateLegacy(final String text) {
        return BUKKIT_MESSAGE.parse(text).toString();
    }

    public static List<String> translateLegacy(final List<String> text) {
        return text.stream().map(ColorUtil::translateLegacy).collect(Collectors.toList());
    }

}
