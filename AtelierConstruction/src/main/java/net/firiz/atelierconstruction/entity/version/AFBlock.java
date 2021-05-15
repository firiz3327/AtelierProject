package net.firiz.atelierconstruction.entity.version;

import net.firiz.ateliercommonapi.FakeId;
import net.firiz.atelierconstruction.ReflectionUtils;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
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
        p1 = ReflectionUtils.set(
                new PacketPlayOutSpawnEntity(this, Block.getCombinedId(getBlock())),
                PacketPlayOutSpawnEntity.class,
                "a",
                fakeId
        ).get();
        p2 = new PacketPlayOutEntityMetadata(fakeId, getDataWatcher(), true);
    }

    public Location getLocation() {
        return location;
    }

    public Packet<?> createMovePacket(Location location) {
        return ReflectionUtils.set(
                new PacketPlayOutEntityTeleport(),
                PacketPlayOutEntityTeleport.class,
                "a",
                fakeId
        ).set("b", location.getX())
                .set("c", location.getY())
                .set("d", location.getZ())
                .set("e", (byte) ((int) (yaw * 256.0F / 360.0F)))
                .set("f", (byte) ((int) (pitch * 256.0F / 360.0F)))
                .set("g", isOnGround())
                .get();
    }

    @Override
    public void tick() {
        ticksLived = 580;
    }

    public void spawn() {
        world.addEntity(this);
    }

    public void sendSpawnPacket(Player player) {
        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(p1);
        playerConnection.sendPacket(p2);
    }

    public void fakeSpawn() {
        world.getWorld().getPlayers().forEach(this::sendSpawnPacket);
    }

}
