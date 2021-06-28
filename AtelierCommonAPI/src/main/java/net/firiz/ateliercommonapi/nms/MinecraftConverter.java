package net.firiz.ateliercommonapi.nms;

import net.firiz.ateliercommonapi.nms.entity.player.NMSPlayer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public final class MinecraftConverter {

    public static Entity convertNMS(org.bukkit.entity.Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }

    public static EntityPlayer convertNMS(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    public static NMSPlayer convert(Player player) {
        return new NMSPlayer(convertNMS(player));
    }

    public static PlayerConnection connection(Player player) {
        return convertNMS(player).b;
    }

}
