package net.firiz.atelierconstruction.world.animation;

import net.firiz.atelierconstruction.utils.LocationComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.List;

public class GardenAnimation implements Animation {

    private final List<Location> sendingSmokeLocation = new LocationComponent(
            world, -15.5, 69, -0.5
    ).gravity(-18.5, 72, 4.5, 0.8, 10, 10).get();

    private final List<Location> circleRainLocation = new LocationComponent(
            world, -18.5, 75, 4.5
    ).circle(3, 10, false).get();

    private int i = 0;
    private int j = 0;
    private int coolTime = 0;

    @Override
    public void init() {
        // ignored
    }

    @Override
    public void anim() {
        final Location loc = circleRainLocation.get(j);
        world.spawnParticle(Particle.VILLAGER_HAPPY, loc.clone().add(0.5, -3.5, 0.5), 3, 0, 2, 0);
        j++;
        if (j >= circleRainLocation.size()) {
            j = 0;
        }
        if (coolTime == 0 && i < sendingSmokeLocation.size()) {
            final Location location = sendingSmokeLocation.get(i);
            if (i == 0) {
                world.spawnParticle(Particle.END_ROD, location, 10, 0.3, 0.3, 0.3, 0);
                world.spawnParticle(Particle.SOUL_FIRE_FLAME, location, 10, 0.1, 0.1, 0.1, 0);
            } else {
                world.spawnParticle(Particle.END_ROD, location, 3, 0.1, 0.1, 0.1, 0);
                world.spawnParticle(Particle.SOUL_FIRE_FLAME, location, 2, 0.1, 0.1, 0.1, 0);
            }
            i++;
        } else if (coolTime != 0) {
            coolTime--;
        } else {
            i = 0;
            coolTime = 100;
        }
    }

    @Override
    public void joinWorld(Player player) {
    }
}
