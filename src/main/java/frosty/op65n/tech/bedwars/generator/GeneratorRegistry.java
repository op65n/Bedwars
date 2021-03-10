package frosty.op65n.tech.bedwars.generator;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.generator.impl.ItemGenerator;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.FileUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public final class GeneratorRegistry {

    private static final FileConfiguration CONFIGURATION = FileUtil.getConfiguration(PathRegistry.GENERATOR_SETTINGS.getPath());
    private static final Map<String, ItemGenerator> GENERATOR_REGISTRY = new HashMap<>();

    public static void enable(final BedwarsPlugin plugin) {
        final ConfigurationSection section = CONFIGURATION.getConfigurationSection("generator");

        if (section == null) {
            return;
        }

        for (final String key : section.getKeys(false)) {
            GENERATOR_REGISTRY.put(key, new ItemGenerator(key, CONFIGURATION));
        }
    }

    public static ItemGenerator getGenerator(final String identifier) {
        return GENERATOR_REGISTRY.get(identifier);
    }

    public static Map<String, ItemGenerator> getGeneratorRegistry() {
        return GENERATOR_REGISTRY;
    }
}
