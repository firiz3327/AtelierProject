package net.firiz.atelierconstruction.entity.version;

import net.firiz.ateliercommonapi.FakeId;
import net.firiz.ateliercommonapi.nms.packet.EntityPacket;
import net.firiz.ateliercommonapi.nms.packet.PacketUtils;
import net.firiz.atelierconstruction.ReflectionUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.level.block.Block;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.entity.Player;

public class AFBlock extends EntityFallingBlock {

    private final int fakeId;
    private final Location location;
    private final Packet<?> p1;
    private final Packet<?> p2;

    public AFBlock(Location location, BlockData blockData) {
        super(
                ((CraftWorld) location.getWorld()).getHandle(),
                location.getX() + 0.5,
                location.getY() - 0.01,
                location.getZ() + 0.5,
                ((CraftBlockData) blockData).getState()
        );
        this.fakeId = FakeId.createId();
        this.location = location;
        setNoGravity(true);
        e(fakeId);
        p1 = new PacketPlayOutSpawnEntity(this, Block.getCombinedId(getBlock()));
        p2 = new PacketPlayOutEntityMetadata(fakeId, getDataWatcher(), true);
    }

    public Location getLocation() {
        return location;
    }

    public Packet<?> createMovePacket(Location location) {
        return EntityPacket.teleportPacket(fakeId, location, isOnGround());
//        return ReflectionUtils.set(
//                new PacketPlayOutEntityTeleport(),
//                PacketPlayOutEntityTeleport.class,
//                "a",
//                fakeId
//        ).set("b", location.getX())
//                .set("c", location.getY())
//                .set("d", location.getZ())
//                .set("e", (byte) ((int) (yaw * 256.0F / 360.0F)))
//                .set("f", (byte) ((int) (pitch * 256.0F / 360.0F)))
//                .set("g", isOnGround())
//                .get();
    }

    @Override
    public void tick() {
        this.R = 580;
    }

    public void spawn() {
        getWorld().addEntity(this);
    }

    public void sendSpawnPacket(Player player) {
        PacketUtils.sendPackets(player, p1, p2);
    }

    public void fakeSpawn() {
        getWorld().getWorld().getPlayers().forEach(this::sendSpawnPacket);
    }

}
