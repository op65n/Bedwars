package frosty.op65n.tech.bedwars.registry.object;

import frosty.op65n.tech.bedwars.BedwarsPlugin;
import frosty.op65n.tech.bedwars.arena.team.TeamRegistry;
import frosty.op65n.tech.bedwars.registry.Registerable;
import frosty.op65n.tech.bedwars.util.ActionUtil;
import frosty.op65n.tech.bedwars.util.FileUtil;

public final class ObjectRegistration implements Registerable {

    @Override
    public void enable(final BedwarsPlugin plugin) {
        ActionUtil.getActionHandler().loadDefaults(true);

        FileUtil.saveResources(
                "data/lobby-settings.yml",
                "data/lobby-selector.yml",
                "data/setting/team-registration.yml",
                "data/setting/generator-registration.yml",
                "data/arena/pog-arena.yml",
                "data/arena/test-arena.yml"
        );

        TeamRegistry.enable(plugin);
    }
}
