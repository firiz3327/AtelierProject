package net.firiz.atelierconstruction.executor;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class FallingBlockExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            final ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            if (item.getType() != Material.AIR) {
                final ItemMeta meta = item.getItemMeta();
                meta.displayName(Component.text("fallingBlock"));
                item.setItemMeta(meta);
            }
        }
        return true;
    }

}
