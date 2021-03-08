package frosty.op65n.tech.bedwars.action.type;

import frosty.op65n.tech.bedwars.action.ActionExecutable;
import frosty.op65n.tech.bedwars.action.impl.ArenaSelectorAction;

public enum ActionType {

    NONE(null),
    OPEN_SELECTOR(new ArenaSelectorAction()),
    ;

    private final ActionExecutable executable;

    ActionType(final ActionExecutable executable) {
        this.executable = executable;
    }

    public ActionExecutable getExecutable() {
        return this.executable;
    }

    public static ActionType fromString(final String input) {
        if (input == null) {
            return NONE;
        }

        ActionType result = NONE;
        for (final ActionType type : values()) {
            if (!type.name().equalsIgnoreCase(input)) {
                continue;
            }

            result = type;
            break;
        }

        return result;
    }

}
