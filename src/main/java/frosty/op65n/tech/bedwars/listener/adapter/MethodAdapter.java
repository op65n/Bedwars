package frosty.op65n.tech.bedwars.listener.adapter;

import org.bukkit.event.Event;

import java.util.function.Predicate;

public final class MethodAdapter {

    private final Predicate<Event> predicate;
    private final String event;

    public MethodAdapter(final String event, final Predicate<Event> predicate) {
        this.event = event;
        this.predicate = predicate;
    }

    public String getEvent() {
        return this.event;
    }

    public Predicate<Event> getPredicate() {
        return this.predicate;
    }

}
