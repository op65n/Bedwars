package frosty.op65n.tech.bedwars.game.arena.impl;

import frosty.op65n.tech.bedwars.generator.impl.ItemGenerator;

public final class GeneratorHolder {

    private final ItemGenerator generator;
    private long generationSpeed;

    public GeneratorHolder(final ItemGenerator generator) {
        this.generator = generator;
        this.generationSpeed = generator.getDefaultGenerationSpeed();
    }

    public ItemGenerator getGenerator() {
        return generator;
    }

    public long getGenerationSpeed() {
        return generationSpeed;
    }

    public void setGenerationSpeed(final long value) {
        this.generationSpeed = value;
    }
}
