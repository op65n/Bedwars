package frosty.op65n.tech.bedwars.util;

import com.github.frcsty.frozenactions.util.Replace;
import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.game.arena.ArenaRegistry;
import frosty.op65n.tech.bedwars.game.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.util.gui.components.ItemBuilder;
import frosty.op65n.tech.bedwars.util.gui.menu.item.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public final class ArenaUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    @SuppressWarnings("ConstantConditions")
    public static Set<GuiItem> constructArenaItems(final FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("arena-items");
        final Set<GuiItem> result = new HashSet<>();

        for (final ArenaObject arena : ArenaRegistry.getArenaRegistry().values()) {
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
                    "{arena-type}", arena.getType(),
                    "{arena_team_size}", arena.getTeamSize(),
                    "{arena_participants}", arena.getLobbyParticipants(),
                    "{arena_size}", arena.getSize()
            )));
            result.add(new GuiItem(builder.build()));
        }

        return result;
    }

}
