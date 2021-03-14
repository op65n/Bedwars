package frosty.op65n.tech.bedwars.util;

import com.github.frcsty.frozenactions.util.Replace;
import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.game.arena.ArenaRegistry;
import frosty.op65n.tech.bedwars.game.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.game.arena.impl.TeamHolder;
import frosty.op65n.tech.bedwars.game.team.setting.TeamSetting;
import frosty.op65n.tech.bedwars.util.gui.components.ItemBuilder;
import frosty.op65n.tech.bedwars.util.gui.menu.item.GuiItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public final class ArenaUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);

    /**
     * Constructs gui items for the arena selector
     *
     * @param configuration arena selector configuration
     * @return Set of items constructed from the loaded arenas
     */
    @SuppressWarnings("ConstantConditions")
    public static Set<GuiItem> constructArenaItems(final FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("arena-items");
        final Set<GuiItem> result = new HashSet<>();

        for (final ArenaObject arena : ArenaRegistry.getArenaRegistry().values()) {
            final Material material = Material.matchMaterial(section.getString("material"));
            if (material == null) {
                LoggerUtil.log(Level.WARNING, "Given material key '%s' is not valid!", section.getString("material"));
                continue;
            }

            final ItemBuilder builder = ItemBuilder.from(material);
            builder.setName(ColorUtil.translateLegacy(Replace.replaceString(
                    section.getString("display"),
                    "{arena_identifier}", arena.getIdentifier()
            )));
            builder.setLore(ColorUtil.translateLegacy(Replace.replaceList(
                    section.getStringList("lore"),
                    "{arena_type}", arena.getType(),
                    "{arena_team_size}", arena.getTeamSize(),
                    "{arena_participants}", arena.getLobbyParticipants(),
                    "{arena_size}", arena.getSize()
            )));
            result.add(new GuiItem(builder.build(), event -> {
                final Player viewer = (Player) event.getWhoClicked();

                if (arena.getLobbyParticipants() >= arena.getSize()) {
                    return;
                }

                viewer.teleport(arena.getLobbyLocation());
            }));
        }

        return result;
    }

    /**
     * Constructs gui items for the team selector
     *
     * @param configuration team selector configuration
     * @return Set of items constructed from the loaded teams
     */
    @SuppressWarnings("ConstantConditions")
    public static Set<GuiItem> constructTeamItems(final ArenaObject arena, final FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("team-items");
        final Set<GuiItem> result = new HashSet<>();

        for (final TeamHolder team : arena.getTeams().values()) {
            final TeamSetting setting = team.getSetting();
            final String materialString = Replace.replaceString(
                    section.getString("material"),
                    "{team_material_color}", setting.getMaterialColor()
            );
            final Material material = Material.matchMaterial(materialString);
            if (material == null) {
                LoggerUtil.log(Level.WARNING, "Given material key '%s' is not valid!", materialString);
                continue;
            }

            final ItemBuilder builder = ItemBuilder.from(material);
            builder.setName(ColorUtil.translateLegacy(Replace.replaceString(
                    section.getString("display"),
                    "{team_color}", setting.getColor(),
                    "{team_display}", setting.getDisplay()
            )));
            builder.setLore(ColorUtil.translateLegacy(Replace.replaceList(
                    section.getStringList("lore"),
                    "{team_participants_size}", team.getParticipants().size(),
                    "{team_size}", arena.getTeamSize()

            )));
            result.add(new GuiItem(builder.build(), event -> {
                final Player viewer = (Player) event.getWhoClicked();

                if (team.getParticipants().size() >= arena.getTeamSize()) {
                    return;
                }

                team.addParticipant(viewer);
            }));
        }

        return result;
    }

}
