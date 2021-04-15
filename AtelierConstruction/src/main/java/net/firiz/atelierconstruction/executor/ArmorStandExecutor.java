package net.firiz.atelierconstruction.executor;

import net.firiz.atelierconstruction.manager.ArmorStandManagerV2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArmorStandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            return ArmorStandManagerV2.INSTANCE.command((Player) sender, args);
        }
        return true;
    }

}
