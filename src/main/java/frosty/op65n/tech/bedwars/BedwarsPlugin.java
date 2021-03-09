package frosty.op65n.tech.bedwars;

import frosty.op65n.tech.bedwars.arena.ArenaRegistration;
import frosty.op65n.tech.bedwars.registry.object.ObjectRegistration;
import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.lobby.LobbyProvider;
import frosty.op65n.tech.bedwars.registry.Registerable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.LinkedHashSet;

public final class BedwarsPlugin extends JavaPlugin {

    private static final LinkedHashSet<Registerable> REGISTERABLES = new LinkedHashSet<>(Arrays.asList(
            new ObjectRegistration(), new ListenerRegistration(), new LobbyProvider(), new ArenaRegistration()
    ));

    @Override
    public void onEnable() {
        REGISTERABLES.forEach(it -> it.enable(this));
    }

    @Override
    public void onDisable() {
        REGISTERABLES.forEach(it -> it.disable(this));
    }

}
