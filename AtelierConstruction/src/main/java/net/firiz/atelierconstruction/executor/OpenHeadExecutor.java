package net.firiz.atelierconstruction.executor;

import net.firiz.atelierconstruction.manager.SkinProperty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenHeadExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            SkinProperty.openHeads((Player) sender);
        }
        return true;
    }

}
