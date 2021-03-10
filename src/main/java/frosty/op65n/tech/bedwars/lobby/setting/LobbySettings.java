package frosty.op65n.tech.bedwars.lobby.setting;

import frosty.op65n.tech.bedwars.util.LocationUtil;
import frosty.op65n.tech.bedwars.util.item.ItemBuilder;
import frosty.op65n.tech.bedwars.util.item.ItemHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LobbySettings {

    private final World lobbyWorld;
    private final Location spawnLocation;

    private final List<String> onJoinActions = new ArrayList<>();
    private final List<String> onQuitActions = new ArrayList<>();

    private final Set<ItemHolder> givenItemsOnJoin = new HashSet<>();

    public LobbySettings(final FileConfiguration configuration) {
        this.lobbyWorld = Bukkit.getWorld(configuration.getString("world.world-identifier"));
        this.spawnLocation = LocationUtil.fromString(lobbyWorld, configuration.getString("world.spawn"));

        loadItemsOnJoin(configuration);
    }

    private void loadItemsOnJoin(final FileConfiguration configuration) {
        final ConfigurationSection itemsSection = configuration.getConfigurationSection("behavior.give-items-on-join");

        if (itemsSection == null) return;
        for (final String key : itemsSection.getKeys(false)) {
            final ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);

            if (itemSection == null) continue;

            givenItemsOnJoin.add(new ItemHolder(
                    ItemBuilder.fromSection(key, itemSection),
                    itemSection.getInt("slot", -1)
            ));
        }

        this.onJoinActions.addAll(configuration.getStringList("world.join-actions"));
        this.onQuitActions.addAll(configuration.getStringList("world.quit-actions"));
    }

    public Set<ItemHolder> getGivenItemsOnJoin() {
        return this.givenItemsOnJoin;
    }

    public Location getSpawnLocation() {
        return this.spawnLocation;
    }

    public World getLobbyWorld() {
        return this.lobbyWorld;
    }

    public List<String> getOnJoinActions() {
        return this.onJoinActions;
    }

    public List<String> getOnQuitActions() {
        return this.onQuitActions;
    }
}
