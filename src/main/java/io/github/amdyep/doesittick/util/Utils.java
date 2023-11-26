package io.github.amdyep.doesittick.util;

import io.github.amdyep.doesittick.api.DoesItTickManager;
import io.github.amdyep.doesittick.api.Extended$EntityType;
import io.github.amdyep.doesittick.cfg.DoesItTickConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static boolean isNearPlayer(@NotNull Level world, BlockPos pos, int maxHeight, int maxDistanceSquare) {
        for (Player player : world.players()) {
            if (Math.abs(player.getY() - pos.getY()) < maxHeight) {
                double x = player.getX() - pos.getX();
                double z = player.getZ() - pos.getZ();
                if ((x * x + z * z) < maxDistanceSquare) return true;
            }
        }
        return false;
    }

    public static boolean interceptGuardEntityTick(@NotNull Entity entity) {
        int horizontal = DoesItTickConfig.MAX_HORIZONTAL_TICK_DISTANCE.get();
        int vertical = DoesItTickConfig.MAX_VERTICAL_TICK_DISTANCE.get();
        if (entity instanceof Player ||
                ((Extended$EntityType) entity.getType()).doesittick$alwaysTick() ||
                DoesItTickManager.INSTANCE.checkEntity(entity)) return true;
        if (entity instanceof ItemEntity) {
            if (!DoesItTickConfig.OPTIMIZE_ITEM_MOVEMENT.get()) return true;
            return (ThreadLocalRandom.current().nextInt(3) > 1);
        }
        return !(entity instanceof LivingEntity living) ||
                (DoesItTickConfig.IGNORE_DEAD_ENTITIES.get() && living.isDeadOrDying()) ||
                isNearPlayer(entity.level(), entity.blockPosition(), vertical, horizontal * horizontal);
    }
}
