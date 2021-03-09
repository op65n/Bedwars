package frosty.op65n.tech.bedwars.lobby;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.lobby.listener.LobbyListener;
import frosty.op65n.tech.bedwars.lobby.setting.LobbySettings;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.FileUtil;
import org.bukkit.Bukkit;

public final class LobbyProvider implements Registerable {

    private LobbySettings settings;

    @Override
    public void enable(final BedwarsPlugin plugin) {
        this.settings = new LobbySettings(FileUtil.getConfiguration(PathRegistry.LOBBY_SETTINGS.getPath()));

        ListenerRegistration.addListener(new LobbyListener(this.settings));
    }

    @Override
    public void disable(final BedwarsPlugin plugin) {
        Bukkit.getOnlinePlayers().forEach(it -> it.getInventory().clear());
    }

    public LobbySettings getSettings() {
        return this.settings;
    }
}
