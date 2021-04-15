package net.firiz.atelierconstruction.world.animation;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public interface Animation {

    Random randomizer = new Random();
    @NotNull
    World world = Objects.requireNonNull(Bukkit.getWorld("world"));

    void init();

    void anim();

    void joinWorld(Player player);

}
