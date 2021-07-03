package net.firiz.ateliercommonapi.nms.entity;

import net.kyori.adventure.text.Component;
import net.minecraft.world.entity.npc.EntityVillager;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Villager;

public class NMSVillager extends NMSLivingEntity {

    public NMSVillager(EntityVillager entity) {
        super(entity);
    }

    @Override
    public Villager entity() {
        return (Villager) super.entity();
    }

    @Override
    public EntityVillager nms() {
        return (EntityVillager) super.nms();
    }

    public static NMSVillager create(Location location, Component name, Villager.Type type, Villager.Profession profession) {
        final CraftWorld craftWorld = (CraftWorld) location.getWorld();
        final EntityVillager entityVillager = (EntityVillager) craftWorld.createEntity(location, Villager.class);
        final Villager villager = (Villager) entityVillager.getBukkitEntity();
        entityVillager.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entityVillager.setCustomNameVisible(true);
        villager.customName(name);
        villager.setVillagerType(type);
        villager.setProfession(profession);
        final NMSVillager result = new NMSVillager(entityVillager);
        result.insertEntityFakeId();
        return result;
    }
}
