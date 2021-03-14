package frosty.op65n.tech.bedwars.listener.base;

import frosty.op65n.tech.bedwars.listener.ListenerRegistration;
import frosty.op65n.tech.bedwars.listener.adapter.bus.SimpleEventBus;
import frosty.op65n.tech.bedwars.listener.base.event.PlayerWorldJoinEvent;
import frosty.op65n.tech.bedwars.listener.base.event.PlayerWorldLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.PluginManager;

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
    public void onPlayerWorldChange(final PlayerChangedWorldEvent event) {
        final PluginManager manager = Bukkit.getServer().getPluginManager();
        final Player player = event.getPlayer();

        manager.callEvent(new PlayerWorldLeaveEvent(player, event.getFrom()));
        manager.callEvent(new PlayerWorldJoinEvent(player, player.getWorld()));
    }

    @EventHandler
    public void onPlayerWorldJoin(final PlayerWorldJoinEvent event) {
        handle(event, event.getWorld());
    }

    @EventHandler
    public void onPlayerWorldLeave(final PlayerWorldLeaveEvent event) {
        handle(event, event.getWorld());
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
        final SimpleEventBus eventBus = registration.getEventBusForWorld(world);

        System.out.println("Executed " + eventBus.getIdentifier().toString());
        eventBus.post(event);
    }

}
