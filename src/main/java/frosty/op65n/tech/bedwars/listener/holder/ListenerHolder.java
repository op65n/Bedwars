package frosty.op65n.tech.bedwars.listener.holder;

import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;

import java.util.HashSet;
import java.util.Set;

public final class ListenerHolder {

    private final Set<ListenerAdapter> adapters = new HashSet<>();

    public void addAdapter(final ListenerAdapter adapter) {
        this.adapters.add(adapter);
    }

    public Set<ListenerAdapter> getAdapters() {
        return this.adapters;
    }

}
