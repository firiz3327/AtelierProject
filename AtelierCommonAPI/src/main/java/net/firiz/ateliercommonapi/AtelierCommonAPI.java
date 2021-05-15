package net.firiz.ateliercommonapi;

import net.firiz.ateliercommonapi.loop.LoopManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AtelierCommonAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        LoopManager.INSTANCE.start();
        final ACAExec exec = new ACAExec();
        Objects.requireNonNull(getCommand("ateliercommonapi")).setExecutor(exec);
        Objects.requireNonNull(getCommand("loops")).setExecutor(exec);
    }

    @Override
    public void onDisable() {
        LoopManager.INSTANCE.stop();
    }

    public static AtelierCommonAPI getPlugin() {
        return JavaPlugin.getPlugin(AtelierCommonAPI.class);
    }

}
