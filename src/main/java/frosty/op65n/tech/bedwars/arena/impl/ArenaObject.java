package frosty.op65n.tech.bedwars.arena.impl;

import frosty.op65n.tech.bedwars.arena.impl.state.GameState;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public final class ArenaObject {

    private final String identifier;
    private final String worldIdentifier;

    private final boolean enabled;

    private final int arenaSize;

    private final int teamAmount;
    private final int teamSize;

    // cosmetic

    private final String authorSeparator;
    private final List<String> arenaAuthors = new ArrayList<>();

    // game

    private final Set<UUID> participants = new HashSet<>();
    private GameState gameState = GameState.UNAVAILABLE;

    public ArenaObject(final FileConfiguration configuration) {
        this.identifier = configuration.getString("identifier");
        this.worldIdentifier = configuration.getString("world");
        this.enabled = configuration.getBoolean("enabled");

        this.arenaSize = configuration.getInt("behavior.arena-size");
        this.teamAmount = configuration.getInt("behavior.team-amount");
        this.teamSize = configuration.getInt("behavior.team-size");

        this.authorSeparator = configuration.getString("customization.author-separator");
        this.arenaAuthors.addAll(configuration.getStringList("customization.authors"));
    }

    public Set<UUID> getParticipants() {
        return this.participants;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getWorldIdentifier() {
        return worldIdentifier;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getArenaSize() {
        return arenaSize;
    }

    public int getTeamAmount() {
        return teamAmount;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public String getAuthorSeparator() {
        return authorSeparator;
    }

    public List<String> getArenaAuthors() {
        return arenaAuthors;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(final GameState gameState) {
        this.gameState = gameState;
    }
}
