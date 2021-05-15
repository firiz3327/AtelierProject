package net.firiz.atelierconstruction;

import net.firiz.atelierconstruction.executor.ArmorStandExecutor;
import net.firiz.atelierconstruction.executor.FallingBlockExecutor;
import net.firiz.atelierconstruction.executor.OpenHeadExecutor;
import net.firiz.atelierconstruction.executor.OpenCustomBlockExecutor;
import net.firiz.atelierconstruction.world.animation.AnimationManager;
import net.firiz.atelierconstruction.world.regulation.RegulationManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AtelierConstructionV2 extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("asm")).setExecutor(new ArmorStandExecutor());
        Objects.requireNonNull(getCommand("fb")).setExecutor(new FallingBlockExecutor());
        Objects.requireNonNull(getCommand("heads")).setExecutor(new OpenHeadExecutor());
        Objects.requireNonNull(getCommand("customblocks")).setExecutor(new OpenCustomBlockExecutor());
        getServer().getPluginManager().registerEvents(new AListener(), this);
        AnimationManager.INSTANCE.init();
        RegulationManager.INSTANCE.init();
    }

    public static AtelierConstructionV2 getPlugin() {
        return getPlugin(AtelierConstructionV2.class);
    }

}
