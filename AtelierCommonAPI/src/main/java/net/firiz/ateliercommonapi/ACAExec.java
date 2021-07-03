package net.firiz.ateliercommonapi;

import io.papermc.paper.adventure.AdventureComponent;
import net.firiz.ateliercommonapi.loop.LoopManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.SystemUtils;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

class ACAExec implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (command.getName()) {
            case "ateliercommonapi":
                if (commandSender instanceof Player) {
                    int val = 0;
                    Component c = null;
                    for (int i = 0; i < 50; i++) {
                        final Component z = Component.text("0").color(TextColor.color(0, val, 0));
                        if (c == null) {
                            c = z;
                        } else {
                            c = c.append(z);
                        }
                        val += 5;
                    }
                    ((CraftPlayer) commandSender).getHandle().b.sendPacket(
                            new PacketPlayOutChat(
//                                    TestAdventureComponent.of(c),
                                    new AdventureComponent(c),
                                    ChatMessageType.b,
                                    SystemUtils.b
                            )
                    );
                }
                return true;
            case "loops":
                if (commandSender instanceof Server) {
                    final String[] name = {"tick:", "second:", "half:", "minute:"};
                    final var runnableList = LoopManager.INSTANCE.getRunnableList();
                    for (int i = 0, size = runnableList.size(); i < size; i++) {
                        ALogger.log(name[i]);
                        runnableList.get(i).forEach(ALogger::log);
                    }
                }
                return true;
            case "heads":
                if (commandSender instanceof Player) {
                    SkinProperty.openHeads((Player) commandSender);
                }
                return true;
            default:
                return false;
        }
    }

}
