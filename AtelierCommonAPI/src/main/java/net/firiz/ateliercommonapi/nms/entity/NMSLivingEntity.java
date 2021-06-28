package net.firiz.ateliercommonapi.nms.entity;

import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class NMSLivingEntity extends NMSEntity {

    public NMSLivingEntity(EntityLiving entity) {
        super(entity);
    }

    @Override
    public LivingEntity entity() {
        return (LivingEntity) super.entity();
    }

    @Override
    public EntityLiving nms() {
        return (EntityLiving) super.nms();
    }

    public Location eyeLocation() {
        return entity().getEyeLocation();
    }

    public static NMSLivingEntity createLiving(Location location, String name, EntityType type) {
        if(!type.isAlive()) {
            throw new IllegalArgumentException("Its entityType is not LivingEntity.");
        }
        final CraftWorld craftWorld = (CraftWorld) location.getWorld();
        final EntityLiving entity = (EntityLiving) craftWorld.createEntity(location, type.getEntityClass());
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entity.setCustomNameVisible(true);
        entity.getBukkitEntity().setCustomName(name);
        final NMSLivingEntity result = new NMSLivingEntity(entity);
        result.insertEntityFakeId();
        return result;
    }

}
