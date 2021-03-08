package frosty.op65n.tech.bedwars.registry;

import frosty.op65n.tech.bedwars.BedwarsPlugin;

public interface Registerable {

    default void enable(final BedwarsPlugin plugin) {}

    default void disable(final BedwarsPlugin plugin) {}

}
