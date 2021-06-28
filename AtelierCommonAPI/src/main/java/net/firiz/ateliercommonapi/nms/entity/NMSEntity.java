package net.firiz.ateliercommonapi.nms.entity;

import io.papermc.paper.adventure.PaperAdventure;
import net.firiz.ateliercommonapi.FakeId;
import net.kyori.adventure.text.Component;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;

public class NMSEntity {

    private final Entity entity;

    public NMSEntity(Entity entity) {
        this.entity = entity;
    }

    public org.bukkit.entity.Entity entity() {
        return entity.getBukkitEntity();
    }

    public Entity nms() {
        return entity;
    }

    public Location location() {
        return entity().getLocation();
    }

    public World world() {
        return entity().getWorld();
    }

    public int id() {
        return entity.getId();
    }

    public void spawn() {
        entity.getWorld().addEntity(entity);
    }

    public Component customName() {
        final IChatBaseComponent name = entity.getCustomName();
        return name != null ? PaperAdventure.asAdventure(name) : null;
    }

    public void insertEntityFakeId() {
        final int fakeId = FakeId.createId();
        entity.e(fakeId); // Entity.id = fakeId
    }

    public static NMSEntity create(Location location, String name, EntityType type) {
        final CraftWorld craftWorld = (CraftWorld) location.getWorld();
        final Entity entity = craftWorld.createEntity(location, type.getEntityClass());
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entity.setCustomNameVisible(true);
        entity.getBukkitEntity().setCustomName(name);
        final NMSEntity result = new NMSEntity(entity);
        result.insertEntityFakeId();
        return result;
    }

}
