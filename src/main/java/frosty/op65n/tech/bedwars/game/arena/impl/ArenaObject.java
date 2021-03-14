package frosty.op65n.tech.bedwars.game.arena.impl;

import frosty.op65n.tech.bedwars.util.LocationUtil;
import org.bukkit.Location;
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

    private final Location lobbyLocation;

    public ArenaObject(final World world, final FileConfiguration configuration) {
        this.world = world;
        this.identifier = configuration.getString("identifier");

        final ConfigurationSection settingsSection = configuration.getConfigurationSection("settings");
        if (settingsSection == null)
            throw new RuntimeException("Missing 'settings' section within the arena configuration!");

        this.lobbyLocation = LocationUtil.fromString(world, settingsSection.getString("lobby-location"));
        this.type = settingsSection.getString("arena-type");
        this.size = settingsSection.getInt("arena-size");
        this.teamSize = settingsSection.getInt("team-size");

        final ConfigurationSection teamsSection = configuration.getConfigurationSection("team");
        if (teamsSection == null)
            throw new RuntimeException("Missing 'team' section with the arena configuration!");

        for (final String key : teamsSection.getKeys(false)) {
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

    public Location getLobbyLocation() {
        return lobbyLocation;
    }
}
