package frosty.op65n.tech.bedwars.util.gui.components;

import org.bukkit.event.Event;

@FunctionalInterface
public interface GuiAction<T extends Event> {

    /**
     * Executes the task passed to it
     *
     * @param event Inventory action
     */
    public void execute(final T event);
}
