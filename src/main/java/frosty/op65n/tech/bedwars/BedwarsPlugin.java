package frosty.op65n.tech.bedwars;

import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.lobby.LobbyProvider;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.util.ActionUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class BedwarsPlugin extends JavaPlugin {

    private static final Set<Registerable> REGISTERABLES = new HashSet<>(Arrays.asList(
            new ListenerRegistration(), new LobbyProvider()
    ));

    @Override
    public void onEnable() {
        ActionUtil.getActionHandler().loadDefaults(true);
        REGISTERABLES.forEach(it -> it.enable(this));
    }

    @Override
    public void onDisable() {
        REGISTERABLES.forEach(it -> it.disable(this));
    }

}
