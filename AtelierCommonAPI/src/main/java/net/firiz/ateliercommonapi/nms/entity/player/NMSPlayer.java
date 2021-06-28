package net.firiz.ateliercommonapi.nms.entity.player;

import com.mojang.authlib.GameProfile;
import net.firiz.ateliercommonapi.SkinProperty;
import net.firiz.ateliercommonapi.nms.entity.NMSLivingEntity;
import net.kyori.adventure.text.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NMSPlayer extends NMSLivingEntity {

    public NMSPlayer(EntityPlayer entity) {
        super(entity);
    }

    @Override
    public Player entity() {
        return (Player) super.entity();
    }

    @Override
    public EntityPlayer nms() {
        return (EntityPlayer) super.nms();
    }

    public int ping() {
        return entity().getPing();
    }

    public Component displayName() {
        return this.nms().adventure$displayName;
    }

    public GameProfile profile() {
        return this.nms().getProfile();
    }

    public static NMSPlayer create(final World world, final Location location, final UUID uuid, final String name) {
        final GameProfile profile = new GameProfile(uuid, name);
        return create(world, location, profile, name, uuid);
    }

    public static NMSPlayer create(final World world, final Location location, final SkinProperty skinProperty, final String name) {
        final UUID uuid = UUID.randomUUID();
        final GameProfile profile = new GameProfile(uuid, name);
        skinProperty.modifyTextures(profile);
        return create(world, location, profile, name, uuid);
    }

    private static NMSPlayer create(final World world, final Location location, final GameProfile profile, final String name, final UUID uuid) {
        final MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        final WorldServer nmsWorld = ((CraftWorld) world).getHandle();
        final EntityPlayer entityPlayer = new EntityPlayer(server, nmsWorld, profile);
        entityPlayer.listName = CraftChatMessage.fromStringOrNull("npc");
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        final NMSPlayer result = new NMSPlayer(entityPlayer);
        result.insertEntityFakeId();
        return result;
    }

}
