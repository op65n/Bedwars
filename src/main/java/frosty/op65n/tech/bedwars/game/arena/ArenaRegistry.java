package frosty.op65n.tech.bedwars.game.arena;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.game.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.listener.arena.PlayerListener;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.FileUtil;
import frosty.op65n.tech.bedwars.util.LocationUtil;
import frosty.op65n.tech.bedwars.util.LoggerUtil;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

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
            if (!configuration.getBoolean("enabled")) {
                LoggerUtil.log(Level.INFO, "Skipping arena registry for file '%s' as it is not enabled!", arena.getName());
                continue;
            }

            final World world = LocationUtil.getWorldFor(configuration.getString("world"));
            final ArenaObject arenaObject = new ArenaObject(world, configuration);
            ARENA_REGISTRY.put(world, arenaObject);
            ListenerRegistration.addListener(new PlayerListener(world, arenaObject));
        }
    }

    public static Map<World, ArenaObject> getArenaRegistry() {
        return ARENA_REGISTRY;
    }
}
