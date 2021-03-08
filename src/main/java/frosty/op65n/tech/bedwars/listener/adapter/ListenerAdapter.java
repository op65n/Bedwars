package frosty.op65n.tech.bedwars.listener.adapter;

import frosty.op65n.tech.bedwars.listener.type.ListenerType;

import java.util.Set;

public interface ListenerAdapter {

    ListenerType getType();

    Set<MethodAdapter> getMethodAdapters();

}
