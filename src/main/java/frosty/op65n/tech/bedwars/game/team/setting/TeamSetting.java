package frosty.op65n.tech.bedwars.game.team.setting;

import org.bukkit.configuration.ConfigurationSection;

public final class TeamSetting {

    private final String color;
    private final String materialColor;
    private final String display;
    private final String displayLetter;
    private final String symbol;

    public TeamSetting(final ConfigurationSection section) {
        this.color = section.getString("color");
        this.materialColor = section.getString("material-color");
        this.display = section.getString("display");
        this.displayLetter = section.getString("display-letter");
        this.symbol = section.getString("symbol");
    }

    public String getColor() {
        return this.color;
    }

    public String getMaterialColor() {
        return this.materialColor;
    }

    public String getDisplay() {
        return this.display;
    }

    public String getDisplayLetter() {
        return this.displayLetter;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
