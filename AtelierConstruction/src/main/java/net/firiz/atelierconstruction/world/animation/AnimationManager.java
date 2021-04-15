package net.firiz.atelierconstruction.world.animation;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.firiz.ateliercommonapi.loop.LoopManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum AnimationManager {
    INSTANCE;

    private final Map<World, Animation[]> animationList = new Object2ObjectOpenHashMap<>();

    AnimationManager() {
        animationList.put(Animation.world, new Animation[]{
                new CenterAnimation(),
                new GardenAnimation()
        });
    }

    public void init() {
        animationList.values().forEach(animations -> {
            for (final Animation anim : animations) {
                anim.init();
            }
        });
        LoopManager.INSTANCE.addTicks(() -> animationList.values().forEach(animations -> {
            for (final Animation anim : animations) {
                anim.anim();
            }
        }));
    }

    public void joinWorld(@NotNull final Player player) {
        final Animation[] animations = animationList.get(player.getWorld());
        for (final Animation anim : animations) {
            anim.joinWorld(player);
        }
    }

}
