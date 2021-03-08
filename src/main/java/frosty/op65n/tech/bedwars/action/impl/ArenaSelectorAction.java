package frosty.op65n.tech.bedwars.action.impl;

import frosty.op65n.tech.bedwars.action.ActionExecutable;
import frosty.op65n.tech.bedwars.util.ColorUtil;
import frosty.op65n.tech.bedwars.util.FileUtil;
import frosty.op65n.tech.bedwars.util.TaskUtil;
import frosty.op65n.tech.bedwars.util.gui.components.type.GuiType;
import frosty.op65n.tech.bedwars.util.gui.constructor.MenuConstructor;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.Gui;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class ArenaSelectorAction implements ActionExecutable {

    private static final String FILE_PATH = "data/lobby-selector.yml";
    private static final FileConfiguration CONFIGURATION = FileUtil.getConfiguration(FILE_PATH);

    @Override
    public void execute(final Player player) {
        TaskUtil.async(() -> {
            final Gui menu = new Gui(
                    GuiType.valueOf(CONFIGURATION.getString("menu-type")),
                    ColorUtil.translateLegacy(CONFIGURATION.getString("menu-title"))
            );

            final MenuConstructor constructor = new MenuConstructor(menu, CONFIGURATION);
            constructor.loadItems(CONFIGURATION.getConfigurationSection("items"));

            TaskUtil.queue(() -> constructor.getGui().open(player));
        });
    }

}
