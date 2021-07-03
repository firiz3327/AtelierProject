package net.firiz.atelierconstruction;

import net.firiz.ateliercommonapi.nms.packet.PacketUtils;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockChange;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.entity.Player;

public class A {

    public void a(Player p) {
        final Location location = p.getLocation();
        PacketUtils.sendPacket(p, new PacketPlayOutBlockChange(
                new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()),
                ((CraftBlockData) Material.CHEST.createBlockData()).getState()
        ));
    }

}
