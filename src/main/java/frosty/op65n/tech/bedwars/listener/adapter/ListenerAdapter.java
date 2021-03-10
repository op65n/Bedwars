package frosty.op65n.tech.bedwars.listener.adapter;

import org.bukkit.World;

import java.util.Set;

public interface ListenerAdapter {

    World getWorld();

    Set<MethodAdapter> getMethodAdapters();

}
