package frosty.op65n.tech.bedwars.lobby.listener;

import com.github.frcsty.frozenactions.util.Replace;
import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import frosty.op65n.tech.bedwars.action.ActionExecutable;
import frosty.op65n.tech.bedwars.action.ActionRegistry;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;
import frosty.op65n.tech.bedwars.listener.adapter.MethodAdapter;
import frosty.op65n.tech.bedwars.lobby.setting.LobbySettings;
import frosty.op65n.tech.bedwars.util.ActionUtil;
import frosty.op65n.tech.bedwars.util.gui.components.util.ItemNBT;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class LobbyListener implements ListenerAdapter {

    private final ActionHandler actionHandler = ActionUtil.getActionHandler();
    private final LobbySettings settings;

    public LobbyListener(final LobbySettings settings) {
        this.settings = settings;
    }

    @Override
    public World getWorld() {
        return this.settings.getLobbyWorld();
    }

    @Override
    public Set<MethodAdapter> getMethodAdapters() {
        return new HashSet<>(Arrays.asList(
                new MethodAdapter("PlayerJoinEvent", onPlayerJoin()),
                new MethodAdapter("PlayerQuitEvent", onPlayerQuit()),
                new MethodAdapter("PlayerInteractEvent", onItemUse()),
                new MethodAdapter("PlayerMoveEvent", onPlayerMove())
        ));
    }

    private Predicate<Event> onPlayerJoin() {
        return (event) -> {
            final Player player = ((PlayerJoinEvent) event).getPlayer();

            player.teleport(this.settings.getSpawnLocation());

            this.settings.getGivenItemsOnJoin().forEach(it -> {
                final int slot = it.getSlot();
                final ItemStack item = it.getItem();

                if (slot == -1) player.getInventory().addItem(item);
                else player.getInventory().setItem(slot, item);
            });

            this.actionHandler.execute(player, Replace.replaceList(
                    this.settings.getOnJoinActions(),
                    "{player_name}", player.getName()
            ));

            return true;
        };
    }

    private Predicate<Event> onPlayerQuit() {
        return (event) -> {
            final Player player = ((PlayerQuitEvent) event).getPlayer();

            player.getInventory().clear();

            this.actionHandler.execute(player, Replace.replaceList(
                    this.settings.getOnQuitActions(),
                    "{player_name}", player.getName()
            ));

            return true;
        };
    }

    private Predicate<Event> onPlayerMove() {
        return (event) -> {
            final Player player = ((PlayerMoveEvent) event).getPlayer();
            final Location location = player.getLocation();

            if (location.getY() > 0) return true;

            player.teleport(this.settings.getSpawnLocation());
            return true;
        };
    }

    private Predicate<Event> onItemUse() {
        return (event) -> {
            final Player player = ((PlayerInteractEvent) event).getPlayer();
            final Action action = ((PlayerInteractEvent) event).getAction();
            final ItemStack itemStack = player.getInventory().getItemInMainHand();

            if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
                return true;
            }

            final Optional<ActionExecutable> optionalExecutable = ActionRegistry.getExecutableFor(ItemNBT.getNBTTag(itemStack, "action"));
            if (optionalExecutable.isEmpty()) {
                return true;
            }

            final ActionExecutable executable = optionalExecutable.get();
            executable.execute(player);

            return true;
        };
    }

}
