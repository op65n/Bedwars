package frosty.op65n.tech.bedwars.util;

import org.bukkit.Location;
import org.bukkit.World;

public final class LocationUtil {

    /**
     * Deserializes a location from the given format, with all params being
     * optional
     * ie. "X:10 y:10 z:10 pitch:10 yaw:10"
     *
     * @param world the world associated to this location
     * @param input string input to be deserialized
     * @return Location from the given string or a default one
     */
    public static Location fromString(final World world, final String input) {
        final Location location = new Location(world, 0.5, 100, 0.5);
        if (input == null) return location;

        final String[] components = input.split(" ");

        for (final String component : components) {
            if (!component.contains(":")) continue;
            final String[] parts = component.split(":");

            final String key = parts[0];
            final double value = Double.parseDouble(parts[1]);

            switch (key.toLowerCase().trim()) {
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
