package frosty.op65n.tech.bedwars.listener;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;
import frosty.op65n.tech.bedwars.listener.adapter.bus.SimpleEventBus;
import frosty.op65n.tech.bedwars.listener.base.PlayerListener;
import frosty.op65n.tech.bedwars.registry.Registerable;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class ListenerRegistration implements Registerable {

    private static final Map<String, SimpleEventBus> LISTENER_REGISTRY = new HashMap<>();

    @Override
    public void enable(final BedwarsPlugin plugin) {
        Stream.of(
                new PlayerListener(this)
        ).forEach(it ->
                plugin.getServer().getPluginManager().registerEvents(it, plugin)
        );
    }

    /**
     * Adds an adapter to our listener registry
     *
     * @param adapter to be added
     */
    public static void addListener(final ListenerAdapter adapter) {
        final SimpleEventBus eventBus = LISTENER_REGISTRY.getOrDefault(adapter.getWorld().getName(), new SimpleEventBus());

        eventBus.register(adapter);
        LISTENER_REGISTRY.put(adapter.getWorld().getName(), eventBus);
    }

    /**
     * Returns the assigned EventBus for the requested world
     *
     * @param world execution target
     * @return EventBus for the given world
     */
    public SimpleEventBus getEventBusForWorld(final World world) {
        return LISTENER_REGISTRY.getOrDefault(world.getName(), new SimpleEventBus());
    }

}
