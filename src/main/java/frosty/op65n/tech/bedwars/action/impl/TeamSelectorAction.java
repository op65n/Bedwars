package frosty.op65n.tech.bedwars.action.impl;

import frosty.op65n.tech.bedwars.action.ActionExecutable;
import frosty.op65n.tech.bedwars.registry.path.PathRegistry;
import frosty.op65n.tech.bedwars.util.ArenaUtil;
import frosty.op65n.tech.bedwars.util.ColorUtil;
import frosty.op65n.tech.bedwars.util.FileUtil;
import frosty.op65n.tech.bedwars.util.TaskUtil;
import frosty.op65n.tech.bedwars.util.gui.components.type.GuiType;
import frosty.op65n.tech.bedwars.util.gui.constructor.MenuConstructor;
import frosty.op65n.tech.bedwars.util.gui.menu.BaseGui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.Gui;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class TeamSelectorAction implements ActionExecutable {

    private static final FileConfiguration CONFIGURATION = FileUtil.getConfiguration(PathRegistry.TEAM_SELECTOR.getPath());

    @Override
    public void execute(final Player player) {
        TaskUtil.async(() -> {
            final Gui menu = new Gui(
                    GuiType.valueOf(CONFIGURATION.getString("menu-type")),
                    ColorUtil.translateLegacy(CONFIGURATION.getString("menu-title"))
            );

            final MenuConstructor constructor = new MenuConstructor(menu, CONFIGURATION);
            constructor.loadItems(CONFIGURATION.getConfigurationSection("items"));
            final BaseGui gui = constructor.getGui();

            ArenaUtil.constructArenaItems(CONFIGURATION).forEach(gui::addItem);

            TaskUtil.queue(() -> gui.open(player));
        });
    }
}
