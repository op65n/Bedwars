package frosty.op65n.tech.bedwars.util;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public final class FileUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    /**
     * Returns a file at the given path
     *
     * @param path path to the desired file
     * @return File at given path or null
     */
    public static File getFile(final String path) {
        return new File(PLUGIN.getDataFolder(), path);
    }

    /**
     * Loads and returns a file configuration from the file at given path
     *
     * @param path path to the desired configuration
     * @return loaded configuration of the requested file
     */
    public static FileConfiguration getConfiguration(final String path) {
        return YamlConfiguration.loadConfiguration(getFile(path));
    }

    /**
     * Saves the given file paths
     *
     * @param paths paths to the files to be saved
     */
    public static void saveResources(final String... paths) {
        Arrays.stream(paths).forEach(path -> {
            if (!getFile(path).exists())
                PLUGIN.saveResource(path, false);
        });
    }

}
