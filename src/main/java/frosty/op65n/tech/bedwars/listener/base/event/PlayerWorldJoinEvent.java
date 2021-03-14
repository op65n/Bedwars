package frosty.op65n.tech.bedwars.listener.base.event;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PlayerWorldJoinEvent extends Event {

    private final Player player;
    private final World world;

    public PlayerWorldJoinEvent(final Player player, final World world) {
        this.player = player;
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    public Player getPlayer() {
        return this.player;
    }

    // Default Event Stuff

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}
