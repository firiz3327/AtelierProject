package net.firiz.ateliercommonapi.nms.packet;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.firiz.ateliercommonapi.MinecraftVersion;
import net.firiz.ateliercommonapi.nms.MinecraftConverter;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ChunkProviderServer;
import net.minecraft.server.level.PlayerChunkMap;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@MinecraftVersion("1.17")
public final class PacketUtils {

    private PacketUtils() {
    }

    public static void sendPacket(@NotNull final Player player, @NotNull final WrapPacket<?> packet) {
        sendPacket(player, packet.getPacket());
    }

    public static void sendPackets(@NotNull final Player player, @NotNull final WrapPacket<?>... packets) {
        final PlayerConnection playerConnection = MinecraftConverter.connection(player);
        for (final WrapPacket<?> packet : packets) {
            sendPacket(playerConnection, packet.getPacket());
        }
    }

    public static void sendPacket(@NotNull final Player player, @NotNull final Packet<?> packet) {
        sendPacket(MinecraftConverter.connection(player), packet);
    }

    public static void sendPackets(@NotNull final Player player, @NotNull final Packet<?>... packets) {
        final PlayerConnection playerConnection = MinecraftConverter.connection(player);
        for (final Packet<?> packet : packets) {
            sendPacket(playerConnection, packet);
        }
    }

    public static void sendPackets(@NotNull final Player player, @NotNull final Collection<? extends Packet<?>> packets) {
        final PlayerConnection playerConnection = MinecraftConverter.connection(player);
        for (final Packet<?> packet : packets) {
            sendPacket(playerConnection, packet);
        }
    }

    private static void sendPacket(@NotNull final PlayerConnection playerConnection, @NotNull final Packet<?> packet) {
        playerConnection.sendPacket(packet);
    }

    public static void broadcast(@NotNull final Entity entity, @NotNull final World world, @NotNull final Packet<?> packet) {
        ((CraftWorld) world).getHandle().getChunkProvider().broadcast(MinecraftConverter.convertNMS(entity), packet);
    }

    public static Collection<Player> trackPlayer(final Entity entity) {
        return trackPlayer(MinecraftConverter.convertNMS(entity));
    }

    /**
     * {@link ChunkProviderServer#broadcast(net.minecraft.world.entity.Entity, Packet)}を参考に
     * entityがtrackingしているplayerリストを返します
     *
     * @param entity 確認したいentity
     * @return entityがtrackingしているplayerリストを返します
     */
    public static Collection<Player> trackPlayer(final net.minecraft.world.entity.Entity entity) {
        final PlayerChunkMap playerChunkMap = ((WorldServer) entity.getWorld()).getChunkProvider().a;
        final PlayerChunkMap.EntityTracker entityTracker = playerChunkMap.G.get(entity.getId());
        if (entityTracker == null) {
            return Collections.emptySet();
        } else {
            return new ObjectOpenHashSet<>(entityTracker.f) // trackedPlayer
                    .stream()
                    .map(serverPlayerConnection -> serverPlayerConnection.d().getBukkitEntity())
                    .collect(Collectors.toCollection(ObjectOpenHashSet::new));
        }
    }

}
