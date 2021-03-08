package frosty.op65n.tech.bedwars.util.item;

import frosty.op65n.tech.bedwars.util.ColorUtil;
import frosty.op65n.tech.bedwars.util.gui.components.util.ItemNBT;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("ConstantConditions")
public final class ItemBuilder {

    public static ItemStack fromSection(final String materialString, final ConfigurationSection section) {
        final Material material = Material.matchMaterial(materialString);
        ItemStack itemStack = new ItemStack(material, section.getInt("amount", 1));
        final ItemMeta meta = itemStack.getItemMeta();

        if (section.get("display") != null)
            meta.displayName(ColorUtil.translate(section.getString("display")));

        if (section.get("lore") != null)
            meta.lore(ColorUtil.translate(section.getStringList("lore")));

        final ConfigurationSection enchantments = section.getConfigurationSection("enchantments");
        if (enchantments != null) {
            for (final String key : enchantments.getKeys(false)) {
                final Enchantment enchantment = Enchantment.getByName(key);
                final int level = enchantments.getInt(key);

                if (enchantment == null) continue;

                meta.addEnchant(enchantment, level, true);
            }
        }

        if (section.get("flags") != null) {
            for (final String key : section.getStringList("flags")) {
                meta.addItemFlags(ItemFlag.valueOf(key));
            }
        }

        itemStack.setItemMeta(meta);

        final ConfigurationSection effects = section.getConfigurationSection("effects");
        if (effects != null && material == Material.POTION) {
            final PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

            for (final String key : effects.getKeys(false)) {
                final PotionEffectType type = PotionEffectType.getByName(key);

                final ConfigurationSection effect = effects.getConfigurationSection(key);
                if (effect == null) continue;

                final int amplifier = effect.getInt("amplifier");
                final int duration = effect.getInt("duration");

                potionMeta.addCustomEffect(new PotionEffect(type, duration, amplifier, true, true, true), false);
            }

            itemStack.setItemMeta(potionMeta);
        }

        if (section.get("action") != null)
            itemStack = ItemNBT.setNBTTag(itemStack, "action", section.getString("action"));

        return itemStack;
    }

}
