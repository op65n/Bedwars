package frosty.op65n.tech.bedwars.listener;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;
import frosty.op65n.tech.bedwars.listener.adapter.MethodAdapter;
import frosty.op65n.tech.bedwars.listener.base.PlayerListener;
import frosty.op65n.tech.bedwars.listener.holder.ListenerHolder;
import frosty.op65n.tech.bedwars.registry.Registerable;
import org.bukkit.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public final class ListenerRegistration implements Registerable {

    private static final Map<World, ListenerHolder> LISTENER_REGISTRY = new HashMap<>();

    @Override
    public void enable(final BedwarsPlugin plugin) {
        Stream.of(
                new PlayerListener(this)
        ).forEach(it ->
                plugin.getServer().getPluginManager().registerEvents(it, plugin)
        );
    }

        /*
        Stream.of(
                new LobbyListener()
        ).forEach(it -> {
            final ListenerHolder holder = LISTENER_REGISTRY.getOrDefault(it.getType(), new ListenerHolder());

            holder.addAdapter(it);
            LISTENER_REGISTRY.put(it.getType(), holder);
        });
         */

    /**
     * Adds an adapter to our listener registry
     *
     * @param adapter to be added
     */
    public static void addListener(final ListenerAdapter adapter) {
        final ListenerHolder holder = LISTENER_REGISTRY.getOrDefault(adapter.getWorld(), new ListenerHolder());

        holder.addAdapter(adapter);
        LISTENER_REGISTRY.put(adapter.getWorld(), holder);
    }

    /**
     * Returns a set of method adapter which match the given requirements for further execution
     *
     * @param event executed event type
     * @param world requested listeners world
     * @return set of method adapter matching our given requirements
     */
    public Set<MethodAdapter> getMethodAdaptersForEvent(final String event, final World world) {
        final Set<MethodAdapter> result = new HashSet<>();
        final ListenerHolder holder = LISTENER_REGISTRY.getOrDefault(world, new ListenerHolder());

        holder.getAdapters().forEach(it -> {
            for (final MethodAdapter adapter : it.getMethodAdapters()) {
                if (!adapter.getEvent().equalsIgnoreCase(event)) {
                    continue;
                }

                result.add(adapter);
            }
        });

        return result;
    }

}
