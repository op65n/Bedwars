package frosty.op65n.tech.bedwars.listener.arena;

import com.google.common.eventbus.Subscribe;
import frosty.op65n.tech.bedwars.game.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class PlayerListener implements ListenerAdapter {

    private final World world;
    private final ArenaObject arena;

    public PlayerListener(final World world, final ArenaObject arena) {
        this.world = world;
        this.arena = arena;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Subscribe
    public void onWorldSwitch(final PlayerChangedWorldEvent event) {

    }

}
