package frosty.op65n.tech.bedwars.util;

import com.github.frcsty.frozenactions.util.Replace;
import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.arena.ArenaRegistration;
import frosty.op65n.tech.bedwars.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.gui.components.ItemBuilder;
import frosty.op65n.tech.bedwars.util.gui.menu.item.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class ArenaUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    // Fix actual logic
    public static LinkedHashSet<ArenaObject> getSortedByAccessibility(final Set<ArenaObject> arenas) {
        return arenas.stream()
                .sorted((o1, o2) -> o1.getParticipants().size())
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public static Set<ArenaObject> deserialize() {
        final File directory = FileUtil.getFile(PathRegistry.ARENA_DIRECTORY.getPath());

        if (!directory.isDirectory())
            throw new RuntimeException("Some fuckery went on resulting '/data/arena/' not being a directory!");

        final File[] files = directory.listFiles();
        if (files == null)
            throw new RuntimeException("Make some arenas you idiot.");

        final Set<ArenaObject> result = new HashSet<>();
        for (final File arenaFile : files) {
            if (!arenaFile.getName().contains(".yml")) continue;

            result.add(new ArenaObject(YamlConfiguration.loadConfiguration(arenaFile)));
        }

        return result;
    }

    @SuppressWarnings("ConstantConditions")
    public static Set<GuiItem> constructArenaItems(final FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("arena-items");
        final Set<GuiItem> result = new HashSet<>();

        for (final ArenaObject arena : ArenaRegistration.getArenaRegistry()) {
            final Material material = Material.matchMaterial(section.getString("material"));
            if (material == null) {
                Bukkit.getLogger().log(Level.WARNING, "Given material key is not valid! '" + section.getString("material") + "'");
                continue;
            }

            final ItemBuilder builder = ItemBuilder.from(material);
            builder.setName(ColorUtil.translateLegacy(Replace.replaceString(
                    section.getString("display"),
                    "{arena_identifier}", arena.getIdentifier()
            )));
            builder.setLore(ColorUtil.translateLegacy(Replace.replaceList(
                    section.getStringList("lore"),
                    "{arena_authors}", getAuthorsString(arena.getArenaAuthors(), arena.getAuthorSeparator()),
                    "{arena_team_amount}", arena.getTeamAmount(),
                    "{arena_team_size}", arena.getTeamSize(),
                    "{arena_game_state}", arena.getGameState().name(),
                    "{arena_participants}", arena.getParticipants().size(),
                    "{arena_size}", arena.getArenaSize()
            )));
            result.add(new GuiItem(builder.build()));
        }

        return result;
    }

    private static String getAuthorsString(final List<String> authors, final String separator) {
        if (authors.size() == 0) return "/";

        final StringBuilder builder = new StringBuilder();
        for (int index = 0; index < authors.size(); index++) {
            if (index == authors.size() - 1) builder.append(authors.get(index));
            else builder.append(authors.get(index)).append(separator);
        }

        return builder.toString();
    }

}
