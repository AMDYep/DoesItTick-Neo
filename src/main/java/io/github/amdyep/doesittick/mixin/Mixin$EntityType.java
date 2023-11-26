package io.github.amdyep.doesittick.mixin;

import io.github.amdyep.doesittick.api.Extended$EntityType;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityType.class)
public abstract class Mixin$EntityType implements Extended$EntityType {
    @Unique
    private boolean doesittick$alwaysTick;

    @Override
    public boolean doesittick$alwaysTick() {
        return this.doesittick$alwaysTick;
    }

    @Override
    public void doesittick$markAlwaysTick(boolean shouldAlwaysTick) {
        this.doesittick$alwaysTick = shouldAlwaysTick;
    }

}