package frosty.op65n.tech.bedwars.generator.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public final class ItemGenerator {

    private final Set<ItemGenerator> subGenerators = new HashSet<>();
    private final Set<Material> materials = new HashSet<>();

    private final long defaultGenerationSpeed;

    public ItemGenerator(final String key, final FileConfiguration configuration) {
        this.defaultGenerationSpeed = configuration.getLong(String.format("generator.%s.generation-speed", key));

        configuration.getStringList(String.format("generator.%s.sub-generator", key)).forEach(it -> {
            final ItemGenerator generator = new ItemGenerator(it, configuration);

            this.subGenerators.add(generator);
        });

        configuration.getStringList(String.format("generator.%s.material", key)).forEach(it -> {
            final Material material = Material.matchMaterial(it);

            if (material == null) {
                Bukkit.getLogger().log(Level.WARNING, "Material for input key '" + it + "' is not valid!");
                return;
            }

            this.materials.add(material);
        });
    }

    public long getDefaultGenerationSpeed() {
        return this.defaultGenerationSpeed;
    }

    public Set<ItemGenerator> getSubGenerators() {
        return this.subGenerators;
    }

    public Set<Material> getMaterials() {
        return this.materials;
    }
}
