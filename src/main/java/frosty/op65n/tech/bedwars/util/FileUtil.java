package frosty.op65n.tech.bedwars.util;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public final class FileUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    public static File getFile(final String path) {
        return new File(PLUGIN.getDataFolder(), path);
    }

    public static FileConfiguration getConfiguration(final String path) {
        return YamlConfiguration.loadConfiguration(getFile(path));
    }

    public static void saveResources(final String... paths) {
        Arrays.stream(paths).forEach(path -> {
            if (!getFile(path).exists())
                PLUGIN.saveResource(path, false);
        });
    }

}
