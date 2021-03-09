package frosty.op65n.tech.bedwars.registry.path;

public enum PathRegistry {

    // Lobby
    LOBBY_SETTINGS("data/lobby-settings.yml"),
    LOBBY_SELECTOR("data/lobby-selector.yml"),

    // Arena
    ARENA_DIRECTORY("data/arena/"),

    // Team
    TEAM_SETTINGS("data/setting/team-registration.yml")
    ;

    private final String path;

    PathRegistry(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
