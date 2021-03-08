package frosty.op65n.tech.bedwars.action;

import frosty.op65n.tech.bedwars.action.type.ActionType;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public final class ActionRegistry {

    public static Optional<ActionExecutable> getExecutableFor(final ActionType type) {
        return Arrays.stream(ActionType.values())
                .filter(actionType -> actionType == type)
                .map(ActionType::getExecutable)
                .filter(Objects::nonNull)
                .findFirst();
    }

    public static Optional<ActionExecutable> getExecutableFor(final String stringType) {
        return getExecutableFor(ActionType.fromString(stringType));
    }

}
