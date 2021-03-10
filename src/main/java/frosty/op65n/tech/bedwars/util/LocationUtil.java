package frosty.op65n.tech.bedwars.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

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

    public static Location fromString(final World world, @Nullable final String input) {
        final Location location = new Location(world, 0, 100, 0);
        if (input == null) return location;

        final String[] components = input.split(" ");

        for (final String component : components) {
            if (!component.contains(":")) continue;
            final String[] parts = component.split(":");

            final String key = parts[0];
            final double value = Double.parseDouble(parts[1]);
            switch (key.toUpperCase()) {
                case "x" -> location.setX(value);
                case "y" -> location.setY(value);
                case "z" -> location.setZ(value);
                case "pitch" -> location.setPitch((float) value);
                case "yaw" -> location.setYaw((float) value);
            }
        }

        return location;
    }

}
