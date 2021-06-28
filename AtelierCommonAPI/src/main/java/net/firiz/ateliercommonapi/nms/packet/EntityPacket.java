package net.firiz.ateliercommonapi.nms.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.firiz.ateliercommonapi.MinecraftVersion;
import net.firiz.ateliercommonapi.nms.entity.NMSLivingEntity;
import net.firiz.ateliercommonapi.nms.entity.player.PlayerInfoAction;
import net.firiz.ateliercommonapi.nms.entity.player.NMSPlayer;
import net.firiz.ateliercommonapi.utils.array.ArrayUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.UUID;

public final class EntityPacket {

    private EntityPacket() {
    }

    public static PacketPlayOutSpawnEntity spawnPacket(int entityId, Location loc, EntityType entityType) {
        return new PacketPlayOutSpawnEntity(
                entityId,
                UUID.randomUUID(),
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getPitch(),
                loc.getYaw(),
                types(entityType),
                (int) loc.getYaw(),
                new Vec3D(0, 0, 0)
        );
    }

    public static Packet<?>[] spawnLivingPackets(NMSLivingEntity entity) {
        if (entity.nms() == null) {
            throw new IllegalArgumentException("entity is not EntityLiving class");
        }
        final EntityLiving nmsEntity = entity.nms();
        return new Packet[]{
                new PacketPlayOutSpawnEntityLiving(nmsEntity),
                new PacketPlayOutEntityMetadata(
                        entity.id(),
                        nmsEntity.getDataWatcher(),
                        true
                )
        };
    }

    public static PacketPlayOutEntityDestroy despawnPacket(int entityId) {
        return new PacketPlayOutEntityDestroy(entityId);
    }

    public static List<PacketPlayOutEntityDestroy> despawnPackets(int... entityIds) {
        final List<PacketPlayOutEntityDestroy> packets = new ObjectArrayList<>();
        for (final int id : entityIds) {
            packets.add(new PacketPlayOutEntityDestroy(id));
        }
        return packets;
    }

    public static PacketPlayOutEntity.PacketPlayOutEntityLook lookPacket(int entityId, double pitch, double yaw, boolean onGround) {
        return new PacketPlayOutEntity.PacketPlayOutEntityLook(
                entityId,
                (byte) (yaw * 256.0F / 360.0F),
                (byte) (pitch * 256.0F / 360.0F),
                onGround
        );
    }

    @MinecraftVersion("1.17")
    public static PacketPlayOutEntityHeadRotation headRotationPacket(int entityId, double yaw) {
        var wrapPacket = new WrapPacket<>(PacketPlayOutEntityHeadRotation.class);
        wrapPacket.writeByte(entityId);
        wrapPacket.writeByte((byte) (yaw * 256.0F / 360.0F));
        return wrapPacket.getPacket();
    }

    private static PacketPlayOutPlayerInfo playerInfoPacket(final List<NMSPlayer> players, final boolean remove) {
        final List<EntityPlayer> eps = new ObjectArrayList<>();
        players.forEach(vEps -> eps.add(vEps.nms()));
        return new PacketPlayOutPlayerInfo(
                remove ? PlayerInfoAction.REMOVE_PLAYER.nms() : PlayerInfoAction.ADD_PLAYER.nms(),
                eps
        );
    }

    public static PacketPlayOutPlayerInfo playerPacket(final List<NMSPlayer> players, final boolean remove) {
        return playerInfoPacket(players, remove);
    }

    public static Packet<?>[] logoutPlayerPacket(final List<NMSPlayer> players) {
        final int[] ids = new int[players.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = players.get(i).id();
        }
        return ArrayUtils.addAll(new Packet[]{playerInfoPacket(players, true)}, despawnPackets(ids).toArray(new PacketPlayOutEntityDestroy[0]));
    }

    @MinecraftVersion("1.17")
    public static Packet<?>[] skinPackets(final NMSPlayer target, final byte bitmask) {
        final EntityPlayer ePlayer = target.nms();
        final DataWatcher watcher = ePlayer.getDataWatcher();
        // https://wiki.vg/Entity_metadata#Player
        // index (minecraft 1.17) -> 17
        watcher.set(DataWatcherRegistry.a.a(17), bitmask);
        return new Packet[]{
                new PacketPlayOutNamedEntitySpawn(ePlayer),
                new PacketPlayOutEntityMetadata(
                        ePlayer.getId(),
                        watcher,
                        true
                )
        };
    }

    public static PacketPlayOutRecipes recipePacket(final String id, final boolean remove) {
        final List<MinecraftKey> var1 = new ObjectArrayList<>();
        var1.add(new MinecraftKey(id));
        final RecipeBookSettings recipeBookSettings = new RecipeBookSettings();
        /*
        PacketPlayOutRecipes.Action
        a -> INIT
        b -> ADD
        c -> REMOVE
         */
        return new PacketPlayOutRecipes(
                remove ? PacketPlayOutRecipes.Action.c : PacketPlayOutRecipes.Action.b,
                var1,
                new ObjectArrayList<>(),
                recipeBookSettings
        );
    }

    public static PacketPlayOutEntityMetadata metaPacket(int entityId, final DataWatcher dataWatcher) {
        return metaPacket(entityId, dataWatcher, false);
    }

    public static PacketPlayOutEntityMetadata metaPacket(int entityId, final DataWatcher dataWatcher, final boolean value) {
        return new PacketPlayOutEntityMetadata(entityId, dataWatcher, value);
    }

    @MinecraftVersion("1.17")
    public static PacketPlayOutEntityTeleport teleportPacket(int entityId, Location location, boolean onGround) {
        var wrapPacket = new WrapPacket<>(PacketPlayOutEntityTeleport.class);
        wrapPacket.writeByte(entityId);
        wrapPacket.writeDouble(location.getX());
        wrapPacket.writeDouble(location.getY());
        wrapPacket.writeDouble(location.getZ());
        wrapPacket.writeByte((byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        wrapPacket.writeByte((byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
        wrapPacket.writeBoolean(onGround);
        return wrapPacket.getPacket();
    }

    @MinecraftVersion("1.17")
    private static EntityTypes<?> types(EntityType type) {
        return switch (type) {
            case AREA_EFFECT_CLOUD -> EntityTypes.b;
            case ARMOR_STAND -> EntityTypes.c;
            case ARROW -> EntityTypes.d;
            case AXOLOTL -> EntityTypes.e;
            case BAT -> EntityTypes.f;
            case BEE -> EntityTypes.g;
            case BLAZE -> EntityTypes.h;
            case BOAT -> EntityTypes.i;
            case CAT -> EntityTypes.j;
            case CAVE_SPIDER -> EntityTypes.k;
            case CHICKEN -> EntityTypes.l;
            case COD -> EntityTypes.m;
            case COW -> EntityTypes.n;
            case CREEPER -> EntityTypes.o;
            case DOLPHIN -> EntityTypes.p;
            case DONKEY -> EntityTypes.q;
            case DRAGON_FIREBALL -> EntityTypes.r;
            case DROWNED -> EntityTypes.s;
            case ELDER_GUARDIAN -> EntityTypes.t;
            case ENDER_CRYSTAL -> EntityTypes.u;
            case ENDER_DRAGON -> EntityTypes.v;
            case ENDERMAN -> EntityTypes.w;
            case ENDERMITE -> EntityTypes.x;
            case EVOKER -> EntityTypes.y;
            case EVOKER_FANGS -> EntityTypes.z;
            case EXPERIENCE_ORB -> EntityTypes.A;
            case ENDER_SIGNAL -> EntityTypes.B;
            case FALLING_BLOCK -> EntityTypes.C;
            case FIREWORK -> EntityTypes.D;
            case FOX -> EntityTypes.E;
            case GHAST -> EntityTypes.F;
            case GIANT -> EntityTypes.G;
            case GLOW_ITEM_FRAME -> EntityTypes.H;
            case GLOW_SQUID -> EntityTypes.I;
            case GOAT -> EntityTypes.J;
            case GUARDIAN -> EntityTypes.K;
            case HOGLIN -> EntityTypes.L;
            case HORSE -> EntityTypes.M;
            case HUSK -> EntityTypes.N;
            case ILLUSIONER -> EntityTypes.O;
            case IRON_GOLEM -> EntityTypes.P;
            case DROPPED_ITEM -> EntityTypes.Q;
            case ITEM_FRAME -> EntityTypes.R;
            case FIREBALL -> EntityTypes.S;
            case LEASH_HITCH -> EntityTypes.T;
            case LIGHTNING -> EntityTypes.U;
            case LLAMA -> EntityTypes.V;
            case LLAMA_SPIT -> EntityTypes.W;
            case MAGMA_CUBE -> EntityTypes.X;
            case MARKER -> EntityTypes.Y;
            case MINECART -> EntityTypes.Z;
            case MINECART_CHEST -> EntityTypes.aa;
            case MINECART_COMMAND -> EntityTypes.ab;
            case MINECART_FURNACE -> EntityTypes.ac;
            case MINECART_HOPPER -> EntityTypes.ad;
            case MINECART_MOB_SPAWNER -> EntityTypes.ae;
            case MINECART_TNT -> EntityTypes.af;
            case MULE -> EntityTypes.ag;
            case MUSHROOM_COW -> EntityTypes.ah;
            case OCELOT -> EntityTypes.ai;
            case PAINTING -> EntityTypes.aj;
            case PANDA -> EntityTypes.ak;
            case PARROT -> EntityTypes.al;
            case PHANTOM -> EntityTypes.am;
            case PIG -> EntityTypes.an;
            case PIGLIN -> EntityTypes.ao;
            case PIGLIN_BRUTE -> EntityTypes.ap;
            case PILLAGER -> EntityTypes.aq;
            case POLAR_BEAR -> EntityTypes.ar;
            case PRIMED_TNT -> EntityTypes.as;
            case PUFFERFISH -> EntityTypes.at;
            case RABBIT -> EntityTypes.au;
            case RAVAGER -> EntityTypes.av;
            case SALMON -> EntityTypes.aw;
            case SHEEP -> EntityTypes.ax;
            case SHULKER -> EntityTypes.ay;
            case SHULKER_BULLET -> EntityTypes.az;
            case SILVERFISH -> EntityTypes.aA;
            case SKELETON -> EntityTypes.aB;
            case SKELETON_HORSE -> EntityTypes.aC;
            case SLIME -> EntityTypes.aD;
            case SMALL_FIREBALL -> EntityTypes.aE;
            case SNOWMAN -> EntityTypes.aF;
            case SNOWBALL -> EntityTypes.aG;
            case SPECTRAL_ARROW -> EntityTypes.aH;
            case SPIDER -> EntityTypes.aI;
            case SQUID -> EntityTypes.aJ;
            case STRAY -> EntityTypes.aK;
            case STRIDER -> EntityTypes.aL;
            case EGG -> EntityTypes.aM;
            case ENDER_PEARL -> EntityTypes.aN;
            case THROWN_EXP_BOTTLE -> EntityTypes.aO;
            case SPLASH_POTION -> EntityTypes.aP;
            case TRIDENT -> EntityTypes.aQ;
            case TRADER_LLAMA -> EntityTypes.aR;
            case TROPICAL_FISH -> EntityTypes.aS;
            case TURTLE -> EntityTypes.aT;
            case VEX -> EntityTypes.aU;
            case VILLAGER -> EntityTypes.aV;
            case VINDICATOR -> EntityTypes.aW;
            case WANDERING_TRADER -> EntityTypes.aX;
            case WITCH -> EntityTypes.aY;
            case WITHER -> EntityTypes.aZ;
            case WITHER_SKELETON -> EntityTypes.ba;
            case WITHER_SKULL -> EntityTypes.bb;
            case WOLF -> EntityTypes.bc;
            case ZOGLIN -> EntityTypes.bd;
            case ZOMBIE -> EntityTypes.be;
            case ZOMBIE_HORSE -> EntityTypes.bf;
            case ZOMBIE_VILLAGER -> EntityTypes.bg;
            case ZOMBIFIED_PIGLIN -> EntityTypes.bh;
            case PLAYER -> EntityTypes.bi;
            case FISHING_HOOK -> EntityTypes.bj;
            default -> throw new IllegalArgumentException("not support entity type.");
        };
    }

}
