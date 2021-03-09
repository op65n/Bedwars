package frosty.op65n.tech.bedwars.arena.team;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.arena.team.setting.TeamSetting;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class TeamRegistry {

    private static final FileConfiguration CONFIGURATION = FileUtil.getConfiguration(PathRegistry.TEAM_SETTINGS.getPath());
    private static final Map<String, TeamSetting> TEAM_REGISTRY = new HashMap<>();

    public static void enable(final BedwarsPlugin plugin) {
        final ConfigurationSection teamsSection = CONFIGURATION.getConfigurationSection("team");

        if (teamsSection == null) {
            throw new RuntimeException("Failed to find 'team' section of team configuration, can't play bedwars with no teams dumbass.");
        }

        for (final String key : teamsSection.getKeys(false)) {
            final ConfigurationSection teamSection = teamsSection.getConfigurationSection(key);

            if (teamSection == null) {
                Bukkit.getLogger().log(Level.WARNING, "Section for key '" + key + "' is incomplete.");
                continue;
            }

            TEAM_REGISTRY.put(key, new TeamSetting(teamSection));
        }
    }

    public static Map<String, TeamSetting> getTeamRegistry() {
        return TEAM_REGISTRY;
    }
}
