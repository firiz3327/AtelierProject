package net.firiz.ateliercommonapi.nms.entity;

import io.papermc.paper.adventure.AdventureComponent;
import net.firiz.ateliercommonapi.FakeId;
import net.firiz.ateliercommonapi.nms.packet.EntityPacket;
import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

public class EntityData {

    private final int id;
    private final EntityType type;
    @Nullable
    private DataWatcher dataWatcher;

    public EntityData(int id, EntityType type) {
        this(id, type, null);
    }

    public EntityData(int id, EntityType type, @Nullable DataWatcher dataWatcher) {
        this.id = id;
        this.type = type;
        this.dataWatcher = dataWatcher;
    }

    public int id() {
        return id;
    }

    public EntityType type() {
        return type;
    }

    public DataWatcher dataWatcher() {
        return dataWatcher;
    }

    public void dataWatcher(@Nullable DataWatcher dataWatcher) {
        this.dataWatcher = dataWatcher;
    }

    public static EntityData armorStand(World world, Component name, boolean small) {
        final EntityData data = new EntityData(FakeId.createId(), EntityType.ARMOR_STAND);
        armorStand(data, world, name, small);
        return data;
    }

    public static EntityData armorStand(EntityData data, World world, Component name, boolean small) {
        final WorldServer worldServer = ((CraftWorld) world).getHandle();
        final EntityArmorStand armorStand = new EntityArmorStand(worldServer.getMinecraftWorld(), 0, 0, 0);
        armorStand.setCustomName(new AdventureComponent(name));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setSmall(small);
        armorStand.setMarker(true); // 当たり判定がなくなる
        data.dataWatcher(armorStand.getDataWatcher());
        return data;
    }

    public Packet<?> spawnPacket(Location location) {
        return EntityPacket.spawnPacket(id, location, type);
    }

    public Packet<?> despawnPacket() {
        return EntityPacket.despawnPacket(id);
    }

    public Packet<?> metaPacket() {
        return EntityPacket.metaPacket(id, dataWatcher);
    }

    public Packet<?>[] packet(Location location) {
        final Packet<?> spawnPacket = spawnPacket(location);
        if (dataWatcher == null) {
            return new Packet[]{spawnPacket};
        }
        return new Packet[]{spawnPacket, metaPacket()};
    }

}
