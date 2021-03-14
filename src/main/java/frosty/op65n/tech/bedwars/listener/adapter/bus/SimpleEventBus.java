package frosty.op65n.tech.bedwars.listener.adapter.bus;

import com.google.common.eventbus.EventBus;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SimpleEventBus extends EventBus {

    private final Set<ListenerAdapter> subscribers = new HashSet<>();
    private final UUID identifier = UUID.randomUUID();

    public void addAdapter(final ListenerAdapter adapter) { subscribers.add(adapter); }

    public Set<ListenerAdapter> getSubscribers() {
        return this.subscribers;
    }

    public UUID getIdentifier() {
        return this.identifier;
    }
}
