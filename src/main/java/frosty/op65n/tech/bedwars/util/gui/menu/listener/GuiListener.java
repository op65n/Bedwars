package frosty.op65n.tech.bedwars.util.gui.menu.listener;

import frosty.op65n.tech.bedwars.util.gui.components.GuiAction;
import frosty.op65n.tech.bedwars.util.gui.components.util.ItemNBT;
import frosty.op65n.tech.bedwars.util.gui.menu.BaseGui;
import frosty.op65n.tech.bedwars.util.gui.menu.impl.PaginatedGui;
import frosty.op65n.tech.bedwars.util.gui.menu.item.GuiItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class GuiListener implements Listener {

    /**
     * Handles what happens when a player clicks on the GUI
     *
     * @param event The InventoryClickEvent
     */
    @EventHandler
    public void onGuiCLick(final InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGui)) return;

        // Gui
        final BaseGui gui = (BaseGui) event.getInventory().getHolder();

        // Executes the outside click action
        final GuiAction<InventoryClickEvent> outsideClickAction = gui.getOutsideClickAction();
        if (outsideClickAction != null && event.getClickedInventory() == null) {
            outsideClickAction.execute(event);
            return;
        }

        if (event.getClickedInventory() == null) return;

        // Default click action and checks weather or not there is a default action and executes it
        final GuiAction<InventoryClickEvent> defaultTopClick = gui.getDefaultTopClickAction();
        if (defaultTopClick != null && event.getClickedInventory().getType() != InventoryType.PLAYER) {
            defaultTopClick.execute(event);
        }


        // Default click action and checks weather or not there is a default action and executes it
        final GuiAction<InventoryClickEvent> defaultClick = gui.getDefaultClickAction();
        if (defaultClick != null) defaultClick.execute(event);

        // Slot action and checks weather or not there is a slot action and executes it
        final GuiAction<InventoryClickEvent> slotAction = gui.getSlotAction(event.getSlot());
        if (slotAction != null && event.getClickedInventory().getType() != InventoryType.PLAYER) {
            slotAction.execute(event);
        }

        GuiItem guiItem;

        // Checks whether it's a paginated gui or not
        if (gui instanceof PaginatedGui) {
            final PaginatedGui paginatedGui = (PaginatedGui) gui;

            // Gets the gui item from the added items or the page items
            guiItem = paginatedGui.getGuiItem(event.getSlot());
            if (guiItem == null) guiItem = paginatedGui.getPageItem(event.getSlot());

        } else {
            // The clicked GUI Item
            guiItem = gui.getGuiItem(event.getSlot());
        }

        if (!isGuiItem(event.getCurrentItem(), guiItem)) return;

        // Executes the action of the item
        final GuiAction<InventoryClickEvent> itemAction = guiItem.getAction();
        if (itemAction != null) itemAction.execute(event);

    }

    /**
     * Handles what happens when a player clicks on the GUI
     *
     * @param event The InventoryClickEvent
     */
    @EventHandler
    public void onGuiDrag(final InventoryDragEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGui)) return;

        // Gui
        final BaseGui gui = (BaseGui) event.getInventory().getHolder();

        // Default click action and checks weather or not there is a default action and executes it
        final GuiAction<InventoryDragEvent> dragAction = gui.getDragAction();
        if (dragAction != null) dragAction.execute(event);
    }

    /**
     * Handles what happens when the GUI is closed
     *
     * @param event The InventoryCloseEvent
     */
    @EventHandler
    public void onGuiClose(final InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGui)) return;

        // GUI
        final BaseGui gui = (BaseGui) event.getInventory().getHolder();

        // The GUI action for closing
        final GuiAction<InventoryCloseEvent> closeAction = gui.getCloseGuiAction();

        // Checks if there is or not an action set and executes it
        if (closeAction != null && !gui.isUpdating()) closeAction.execute(event);
    }

    /**
     * Handles what happens when the GUI is opened
     *
     * @param event The InventoryOpenEvent
     */
    @EventHandler
    public void onGuiOpen(final InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGui)) return;

        // GUI
        final BaseGui gui = (BaseGui) event.getInventory().getHolder();

        // The GUI action for opening
        final GuiAction<InventoryOpenEvent> openAction = gui.getOpenGuiAction();

        // Checks if there is or not an action set and executes it
        if (openAction != null && !gui.isUpdating()) openAction.execute(event);
    }

    /**
     * Checks if the item is or not a GUI item
     *
     * @param currentItem The current item clicked
     * @param guiItem     The GUI item in the slot
     * @return Whether it is or not a GUI item
     */
    private boolean isGuiItem(@Nullable final ItemStack currentItem, @Nullable final GuiItem guiItem) {
        if (guiItem == null) return false;
        // Checks whether or not the Item is truly a GUI Item
        return ItemNBT.getNBTTag(currentItem, "mf-gui").equals(guiItem.getUuid().toString());
    }

}
