package frosty.op65n.tech.bedwars.util.gui.constructor;

import frosty.op65n.tech.bedwars.util.ColorUtil;
import frosty.op65n.tech.bedwars.util.gui.components.ItemBuilder;
import frosty.op65n.tech.bedwars.util.gui.menu.BaseGui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.Gui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.PaginatedGui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.PersistentGui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.ScrollingGui;
import frosty.op65n.tech.bedwars.util.gui.menu.item.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

@SuppressWarnings("ConstantConditions")
public final class MenuConstructor {

    private final FileConfiguration configuration;
    private final BaseGui gui;

    public MenuConstructor(final Gui gui, final FileConfiguration configuration) {
        this.gui = gui;
        this.configuration = configuration;
    }

    public MenuConstructor(final PaginatedGui gui, final FileConfiguration configuration) {
        this.gui = gui;
        this.configuration = configuration;
    }

    public MenuConstructor(final PersistentGui gui, final FileConfiguration configuration) {
        this.gui = gui;
        this.configuration = configuration;
    }

    public MenuConstructor(final ScrollingGui gui, final FileConfiguration configuration) {
        this.gui = gui;
        this.configuration = configuration;
    }

    public void loadItems(@Nullable final ConfigurationSection section) {
        gui.setRows(configuration.getInt("menu-size") / 9);

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        if (section != null)
            for (final String key : section.getKeys(false)) {
                final ConfigurationSection itemSection = section.getConfigurationSection(key);

                if (itemSection == null) continue;

                final Material material = Material.matchMaterial(itemSection.getString("material"));
                if (material == null) {
                    Bukkit.getLogger().log(Level.WARNING, "Given material key is not valid! '" + itemSection.getString("material") + "'");
                    continue;
                }

                final ItemBuilder builder = ItemBuilder.from(material);
                builder.setName(ColorUtil.translateLegacy(itemSection.getString("display")));
                builder.setLore(ColorUtil.translateLegacy(itemSection.getStringList("lore")));

                final GuiItem item = new GuiItem(builder.build(), event -> {
                    final Player viewer = (Player) event.getWhoClicked();
                    final String action = itemSection.getString("action");

                    if (action == null) return;

                    switch (action.toUpperCase()) {
                        case "CLOSE" -> gui.close(viewer);
                        case "NEXT" -> {
                            if (gui instanceof Gui) return;
                            if (gui instanceof PersistentGui) return;
                            if (gui instanceof PaginatedGui) ((PaginatedGui) gui).next();
                            if (gui instanceof ScrollingGui) ((ScrollingGui) gui).next();
                        }
                        case "PREVIOUS" -> {
                            if (gui instanceof Gui) return;
                            if (gui instanceof PersistentGui) return;
                            if (gui instanceof PaginatedGui) ((PaginatedGui) gui).next();
                            if (gui instanceof ScrollingGui) ((ScrollingGui) gui).next();
                        }
                    }
                });

                if (itemSection.get("slot") != null) gui.setItem(itemSection.getInt("slot"), item);
                else if (itemSection.get("slots") != null) gui.setItem(itemSection.getIntegerList("slots"), item);
            }
    }

    public BaseGui getGui() {
        return this.gui;
    }
}
