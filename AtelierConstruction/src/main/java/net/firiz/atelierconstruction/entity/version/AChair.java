package net.firiz.atelierconstruction.entity.version;

import net.minecraft.world.entity.projectile.EntityTippedArrow;
import org.bukkit.Location;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AChair extends EntityTippedArrow {

    private final Location blockPos;

    public AChair(Location blockPos) {
        super(((CraftWorld) blockPos.getWorld()).getHandle(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
        this.blockPos = blockPos;
        setNoGravity(true);
//        setSmall(true);
//        setInvisible(true);
    }

    public static boolean isChair(org.bukkit.block.Block block) {
        final BlockData data = block.getBlockData();
        if (data instanceof Stairs) {
            final Stairs stairs = (Stairs) data;
            return stairs.getHalf() == Bisected.Half.BOTTOM && !stairs.isWaterlogged() && stairs.getShape() == Stairs.Shape.STRAIGHT;
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (getPassengers().isEmpty() || !isChair(blockPos.getBlock())) {
            die();
        } else {
            // life(arrow)
            au = getWorld().spigotConfig.arrowDespawnRate - 20;
        }
    }

    public void sitDown(Player player) {
        getWorld().addEntity(this);
        ((CraftPlayer) player).getHandle().startRiding(this);
//        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
//        playerConnection.sendPacket(new PacketPlayOutSpawnEntity(this));
//        playerConnection.sendPacket(new PacketPlayOutEntityMetadata(getId(), getDataWatcher(), true));
//        playerConnection.sendPacket(
//                ReflectionUtils.set(
//                        new PacketPlayOutMount(),
//                        PacketPlayOutMount.class,
//                        "a",
//                        this.getId()
//                ).set(
//                        "b",
//                        new int[]{player.getEntityId()}
//                ).get()
//        );
    }

}
