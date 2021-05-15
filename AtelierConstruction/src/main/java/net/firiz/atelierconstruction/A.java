package net.firiz.atelierconstruction;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutBlockChange;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class A {

    public void a(Player p) {
        final EntityPlayer player = ((CraftPlayer) p).getHandle();
        final Location location = p.getLocation();
        player.playerConnection.sendPacket(new PacketPlayOutBlockChange(
                new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()),
                ((CraftBlockData) Material.CHEST.createBlockData()).getState()
        ));
    }

}
