package frosty.op65n.tech.bedwars.listener.base;

import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.listener.adapter.MethodAdapter;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

public final class PlayerListener implements Listener {

    private final ListenerRegistration registration;

    public PlayerListener(final ListenerRegistration registration) {
        this.registration = registration;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        handle(event, event.getPlayer().getWorld());
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        handle(event, event.getPlayer().getWorld());
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        handle(event, event.getPlayer().getWorld());
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        handle(event, event.getPlayer().getWorld());
    }

    private void handle(final Event event, final World world) {
        final Set<MethodAdapter> methods = registration.getMethodAdaptersForEvent(
                event.getClass().getSimpleName(), world
        );

        methods.forEach(it -> it.getPredicate().test(event));
    }

}
