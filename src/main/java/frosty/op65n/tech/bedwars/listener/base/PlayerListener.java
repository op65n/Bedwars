package frosty.op65n.tech.bedwars.listener.base;

import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.listener.adapter.MethodAdapter;
import frosty.op65n.tech.bedwars.listener.type.ListenerType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

public final class PlayerListener implements Listener {

    private final ListenerRegistration registration;

    public PlayerListener(final ListenerRegistration registration) {
        this.registration = registration;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Set<MethodAdapter> methods = registration.getMethodAdaptersForEvent(
                event.getClass().getSimpleName(),
                ListenerType.getTypeForWorld(event.getPlayer().getWorld())
        );

        methods.forEach(it -> it.getPredicate().test(event));
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Set<MethodAdapter> methods = registration.getMethodAdaptersForEvent(
                event.getClass().getSimpleName(),
                ListenerType.getTypeForWorld(event.getPlayer().getWorld())
        );

        methods.forEach(it -> it.getPredicate().test(event));
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Set<MethodAdapter> methods = registration.getMethodAdaptersForEvent(
                event.getClass().getSimpleName(),
                ListenerType.getTypeForWorld(event.getPlayer().getWorld())
        );

        methods.forEach(it -> it.getPredicate().test(event));
    }

}
