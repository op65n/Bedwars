package frosty.op65n.tech.bedwars.lobby;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.lobby.listener.LobbyListener;
import frosty.op65n.tech.bedwars.lobby.setting.LobbySettings;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.util.FileUtil;

public final class LobbyProvider implements Registerable {

    private static final String FILE_PATH = "data/lobby-settings.yml";
    private LobbySettings settings;

    @Override
    public void enable(final BedwarsPlugin plugin) {
        FileUtil.saveResources(
                "data/lobby-settings.yml",
                "data/lobby-selector.yml"
        );

        this.settings = new LobbySettings(FileUtil.getConfiguration(FILE_PATH));

        ListenerRegistration.addListener(new LobbyListener(this.settings));
    }

    public LobbySettings getSettings() {
        return this.settings;
    }
}
