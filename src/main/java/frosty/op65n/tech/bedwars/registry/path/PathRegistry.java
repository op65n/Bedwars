package frosty.op65n.tech.bedwars.registry.path;

public enum PathRegistry {

    // Lobby
    LOBBY_SETTINGS("settings/lobby-settings.yml"),
    ARENA_SELECTOR("selector/arena-selector.yml"),

    // Arena
    ARENA_DIRECTORY("arena/"),

    // Team
    TEAM_SETTINGS("registration/team-registration.yml"),
    TEAM_SELECTOR("selector/team-selector.yml"),

    // Generator
    GENERATOR_SETTINGS("registration/generator-registration.yml")
    ;

    private final String path;

    PathRegistry(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
