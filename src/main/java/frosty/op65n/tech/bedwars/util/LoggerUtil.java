package frosty.op65n.tech.bedwars.util;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class LoggerUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    /**
     * Logs the given information using our logger
     *
     * @param level log level
     * @param text text to be logged
     * @param replacements replacements for the given text
     */
    public static void log(final Level level, final String text, final Object... replacements) {
        PLUGIN.getLogger().log(level, String.format(text, replacements));
    }

}
