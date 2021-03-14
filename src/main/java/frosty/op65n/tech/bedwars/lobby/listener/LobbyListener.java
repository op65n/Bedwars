package frosty.op65n.tech.bedwars.lobby.listener;

import com.github.frcsty.frozenactions.util.Replace;
import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import com.google.common.eventbus.Subscribe;
import frosty.op65n.tech.bedwars.action.ActionExecutable;
import frosty.op65n.tech.bedwars.action.ActionRegistry;
import frosty.op65n.tech.bedwars.listener.adapter.ListenerAdapter;
import frosty.op65n.tech.bedwars.listener.base.event.PlayerWorldJoinEvent;
import frosty.op65n.tech.bedwars.listener.base.event.PlayerWorldLeaveEvent;
import frosty.op65n.tech.bedwars.lobby.setting.LobbySettings;
import frosty.op65n.tech.bedwars.util.ActionUtil;
import frosty.op65n.tech.bedwars.util.gui.components.util.ItemNBT;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

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

    @Subscribe
    public void onPlayerJoin(final PlayerJoinEvent event) {
        handleJoin(event.getPlayer());
    }

    @Subscribe
    public void onPlayerJoinWorld(final PlayerWorldJoinEvent event) {
        handleJoin(event.getPlayer());
    }

    private void handleJoin(final Player player) {
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
    }

    @Subscribe
    public void onPlayerQuit(final PlayerQuitEvent event) {
        handleLeave(event.getPlayer());
    }

    @Subscribe
    public void onPlayerQuitWorld(final PlayerWorldLeaveEvent event) {
        handleLeave(event.getPlayer());
    }

    private void handleLeave(final Player player) {
        player.getInventory().clear();

        this.actionHandler.execute(player, Replace.replaceList(
                this.settings.getOnQuitActions(),
                "{player_name}", player.getName()
        ));
    }

    @Subscribe
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location location = player.getLocation();

        if (location.getY() > 0) return;

        player.teleport(this.settings.getSpawnLocation());
    }

    @Subscribe
    public void onItemUse(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        final Optional<ActionExecutable> optionalExecutable = ActionRegistry.getExecutableFor(ItemNBT.getNBTTag(itemStack, "action"));
        if (optionalExecutable.isEmpty()) {
            return;
        }

        final ActionExecutable executable = optionalExecutable.get();
        executable.execute(player);
    }

}
