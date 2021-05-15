package net.firiz.ateliercommonapi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

class ACAExec implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (command.getName()) {
            case "ateliercommonapi":
                return true;
            case "loops":
            default:
                return false;
        }
    }

}
