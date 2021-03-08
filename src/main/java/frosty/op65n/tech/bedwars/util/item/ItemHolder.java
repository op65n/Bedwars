package frosty.op65n.tech.bedwars.util.item;

import org.bukkit.inventory.ItemStack;

public final class ItemHolder {

    private final ItemStack item;
    private final int slot;

    public ItemHolder(final ItemStack item, final int slot) {
        this.item = item;
        this.slot = slot;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public int getSlot() {
        return this.slot;
    }
}
