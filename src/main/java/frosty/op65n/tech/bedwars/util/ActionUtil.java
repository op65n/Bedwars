package frosty.op65n.tech.bedwars.util;

import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import frosty.op65n.tech.bedwars.BedwarsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ActionUtil {

    private static final BedwarsPlugin PLUGIN = JavaPlugin.getPlugin(BedwarsPlugin.class);
    private static final ActionHandler ACTION_HANDLER = new ActionHandler(PLUGIN);

    public static ActionHandler getActionHandler() {
        return ACTION_HANDLER;
    }
}
