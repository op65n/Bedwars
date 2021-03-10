package frosty.op65n.tech.bedwars.game.arena.impl;

import frosty.op65n.tech.bedwars.game.team.TeamRegistry;
import frosty.op65n.tech.bedwars.game.team.setting.TeamSetting;
import frosty.op65n.tech.bedwars.generator.GeneratorRegistry;
import frosty.op65n.tech.bedwars.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class TeamHolder {

    private final ArenaObject parent;

    private final Location spawn;
    private final Block bed;

    private final TeamSetting setting;
    private final GeneratorHolder generator;
    private final Set<UUID> participants = new HashSet<>();

    public TeamHolder(final ArenaObject arena, final String key, final ConfigurationSection section) {
        this.parent = arena;

        this.setting = TeamRegistry.getSettingFor(key);
        this.generator = new GeneratorHolder(GeneratorRegistry.getGenerator(section.getString("generator")));

        this.spawn = LocationUtil.fromString(arena.getWorld(), section.getString("spawn-location"));
        this.bed = arena.getWorld().getBlockAt(LocationUtil.fromString(arena.getWorld(), section.getString("bed-location")));
    }

    public boolean addParticipant(final Player player) {
        if (this.participants.size() >= this.parent.getTeamSize())
            return false;

        final UUID identifier = player.getUniqueId();
        final Optional<UUID> present = this.participants.stream()
                .filter(it -> it.toString().equalsIgnoreCase(identifier.toString()))
                .findAny();

        if (present.isPresent())
            return false;

        return this.participants.add(player.getUniqueId());
    }

    public boolean removeParticipant(final Player player) {
        final UUID identifier = player.getUniqueId();
        final Optional<UUID> present = this.participants.stream()
                .filter(it -> it.toString().equalsIgnoreCase(identifier.toString()))
                .findAny();

        if (present.isEmpty())
            return false;

        return this.participants.remove(present.get());
    }

    public Block getBed() {
        return this.bed;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public TeamSetting getSetting() {
        return this.setting;
    }

    public GeneratorHolder getGenerator() {
        return this.generator;
    }

    public Set<UUID> getParticipants() {
        return this.participants;
    }
}
