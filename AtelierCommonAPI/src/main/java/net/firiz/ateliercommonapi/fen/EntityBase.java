package net.firiz.ateliercommonapi.fen;

import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class EntityBase<T extends Entity> {

    private float yaw; // 方角 南0 西90 北180 東270
    private float pitch; // 上下 -90 ~ 90
    private Location location;
    private T entity;

    public void angle(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void anglePacket(float yaw, float pitch) {

        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(
                ((CraftEntity) entity).getHandle(),
                (byte) 0
        );
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
