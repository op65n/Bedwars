package frosty.op65n.tech.bedwars.game.arena;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.game.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class ArenaRegistry {

    private static final Map<World, ArenaObject> ARENA_REGISTRY = new HashMap<>();

    public static void enable(final BedwarsPlugin plugin) {
        final File directory = FileUtil.getFile(PathRegistry.ARENA_DIRECTORY.getPath());

        if (!directory.isDirectory()) {
            return;
        }

        final File[] arenas = directory.listFiles();
        if (arenas == null) {
            return;
        }

        for (final File arena : arenas) {
            if (!arena.getName().contains(".yml"))
                continue;

            final FileConfiguration configuration = YamlConfiguration.loadConfiguration(arena);
            if (!configuration.getBoolean("enabled"))
                continue; // add log

            final World world = Bukkit.getWorld(configuration.getString("world"));

            ARENA_REGISTRY.put(world, new ArenaObject(world, configuration));
        }
    }

    public static Map<World, ArenaObject> getArenaRegistry() {
        return ARENA_REGISTRY;
    }
}
