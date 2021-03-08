package frosty.op65n.tech.bedwars.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public final class LocationUtil {

    public static Location fromSection(final World world, final ConfigurationSection section) {
        return new Location(
                world,
                section.getDouble("x", 0),
                section.getDouble("y", 100),
                section.getDouble("z", 0),
                (float) section.getDouble("yaw", 0),
                (float) section.getDouble("pitch", 0)
        );
    }

}
