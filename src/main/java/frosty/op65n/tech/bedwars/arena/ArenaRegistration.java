package frosty.op65n.tech.bedwars.arena;

import com.google.common.collect.ImmutableSet;
import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.arena.impl.ArenaObject;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.util.ArenaUtil;

import java.util.HashSet;
import java.util.Set;

public final class ArenaRegistration implements Registerable {

    private static final Set<ArenaObject> ARENA_REGISTRY = new HashSet<>();

    @Override
    public void enable(final BedwarsPlugin plugin) {
        ARENA_REGISTRY.addAll(ArenaUtil.deserialize());
    }

    public static ImmutableSet<ArenaObject> getArenaRegistry() {
        return ImmutableSet.copyOf(ARENA_REGISTRY);
    }

}
