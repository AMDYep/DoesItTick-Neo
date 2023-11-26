package io.github.amdyep.doesittick.api;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class DoesItTickManager {
    public static final DoesItTickManager INSTANCE = new DoesItTickManager();
    private final List<Predicate<Entity>> handlers = new ArrayList<>();

    public void register(Predicate<Entity> strategy) {
        handlers.add(strategy);
    }

    public boolean checkEntity(Entity entity) {
        for (Predicate<Entity> handler : handlers) {
            if (handler.test(entity)) return true;
        }
        return false;
    }
}
