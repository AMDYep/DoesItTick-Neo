package io.github.amdyep.doesittick.mixin;

import io.github.amdyep.doesittick.util.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin({Level.class})
public abstract class Mixin$Level {
    @Inject(method = "guardEntityTick", at = @At("HEAD"), cancellable = true)
    private <T extends Entity> void doesittick$inject$guardEntityTick(Consumer<T> consumer, T entity, CallbackInfo ci) {
        if (!Utils.interceptGuardEntityTick(entity)) ci.cancel();
    }
}
