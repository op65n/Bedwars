package frosty.op65n.tech.bedwars.listener.type;

import org.bukkit.World;

public enum ListenerType {

    ARENA("arena"),
    LOBBY("world"),
    ;

    private final String world;

    ListenerType(final String world) {
        this.world = world;
    }

    public String getWorld() {
        return this.world;
    }

    public static ListenerType getTypeForWorld(final World world) {
        ListenerType result = ARENA;

        if (world == null)
            return result;

        for (final ListenerType type : values()) {
            if (!type.getWorld().equals(world.getName())) {
                continue;
            }

            result = type;
            break;
        }

        return result;
    }

}
