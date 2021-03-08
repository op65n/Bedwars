package frosty.op65n.tech.bedwars.util;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

@SuppressWarnings("deprecation")
public final class TaskUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    /**
     * Executes the given task Asynchronously
     *
     * @param execution task to be executed
     */
    public static void async(final Runnable execution) {
        SCHEDULER.scheduleAsyncDelayedTask(PLUGIN, execution);
    }

    /**
     * Executes the given task Synchronously
     *
     * @param execution task to be executed
     */
    public static void queue(final Runnable execution) {
        SCHEDULER.scheduleSyncDelayedTask(PLUGIN, execution);
    }

}
