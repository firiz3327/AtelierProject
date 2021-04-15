package net.firiz.ateliercommonapi;

import net.firiz.ateliercommonapi.loop.LoopManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AtelierCommonAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        LoopManager.INSTANCE.start();
    }

    @Override
    public void onDisable() {
        LoopManager.INSTANCE.stop();
    }

    public static AtelierCommonAPI getPlugin() {
        return JavaPlugin.getPlugin(AtelierCommonAPI.class);
    }

    public static LoopManager getLoopManager() {
        return LoopManager.INSTANCE;
    }

}
