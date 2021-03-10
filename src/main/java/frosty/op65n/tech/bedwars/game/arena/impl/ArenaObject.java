package frosty.op65n.tech.bedwars.game.arena.impl;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public final class ArenaObject {

    private final World world;

    private final String identifier;

    private final String type;
    private final int size;
    private final int teamSize;

    private final Map<String, TeamHolder> teams = new HashMap<>();

    public ArenaObject(final World world, final FileConfiguration configuration) {
        this.world = world;

        this.identifier = configuration.getString("identifier");
        this.type = configuration.getString("settings.arena-type");
        this.size = configuration.getInt("settings.arena-size");
        this.teamSize = configuration.getInt("settings.team-size");

        for (final String key : configuration.getConfigurationSection("team").getKeys(false)) {
            final ConfigurationSection section = configuration.getConfigurationSection("team." + key);
            if (section == null) continue;

            teams.put(key, new TeamHolder(
                    this, key, section
            ));
        }
    }

    public int getLobbyParticipants() {
        return this.world.getPlayerCount();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public Map<String, TeamHolder> getTeams() {
        return this.teams;
    }

    public int getTeamSize() {
        return this.teamSize;
    }

    public World getWorld() {
        return this.world;
    }
}
