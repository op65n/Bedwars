package frosty.op65n.tech.bedwars.registry.object;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.game.arena.ArenaRegistry;
import frosty.op65n.tech.bedwars.game.team.TeamRegistry;
import frosty.op65n.tech.bedwars.generator.GeneratorRegistry;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.util.ActionUtil;
import frosty.op65n.tech.bedwars.util.FileUtil;

public final class ObjectRegistration implements Registerable {

    @Override
    public void enable(final BedwarsPlugin plugin) {
        ActionUtil.getActionHandler().loadDefaults(true);

        FileUtil.saveResources(
                "settings/lobby-settings.yml",
                "selector/arena-selector.yml",
                "registration/team-registration.yml",
                "registration/generator-registration.yml",
                "arena/pog-arena.yml",
                "arena/test-arena.yml"
        );

        TeamRegistry.enable(plugin);
        GeneratorRegistry.enable(plugin);
        ArenaRegistry.enable(plugin);
    }
}
