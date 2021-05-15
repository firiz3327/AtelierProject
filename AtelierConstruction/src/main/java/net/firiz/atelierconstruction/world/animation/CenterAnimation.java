package net.firiz.atelierconstruction.world.animation;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.firiz.atelierconstruction.utils.LocationComponent;
import net.firiz.atelierconstruction.entity.version.AFBlock;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class CenterAnimation implements Animation {

    private static final BlockFace[] faces = {BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH};
    private static final int SPLIT_COUNT = 30;

    private final Location center = new Location(world, 3.5, 81, 2.5);
    private final List<Location> circleLocations = new ObjectArrayList<>();
    private final List<BlockParts> blockParts = new ObjectArrayList<>();

    private int next = 0;

    @Override
    public void init() {
        final LocationComponent locationComponent = new LocationComponent(world);
        for (final Facing f : Facing.values()) {
            for (int i = 0; i < 7; i++) {
                double a = 0;
                double b = 0;
                // east
                switch (i) {
                    case 0:
                        a = 5;
                        break;
                    case 1:
                        a = 5;
                        b = 1;
                        break;
                    case 2:
                        a = 5;
                        b = 2;
                        break;
                    case 3:
                        a = 4;
                        b = 3;
                        break;
                    case 4:
                        a = 3;
                        b = 4;
                        break;
                    case 5:
                        a = 2;
                        b = 5;
                        break;
                    case 6:
                        a = 1;
                        b = 5;
                        break;
                    default: // ignored
                        break;
                }
                double v1;
                double v2;
                if (f.a) {
                    v1 = a;
                    v2 = b;
                } else {
                    v1 = b;
                    v2 = a;
                }
                final double x = center.getX() + ((f.plus1 ? 1 : -1) * v1);
                final double z = center.getZ() + ((f.plus2 ? 1 : -1) * v2);
                final double y = center.getY() + randomizer.nextDouble() - 0.5;
                final Location pos = center.clone().set(x, y, z);
                locationComponent.add(pos);
                blockParts.add(BlockParts.random(pos));
            }
        }
        circleLocations.addAll(locationComponent.finely(SPLIT_COUNT).get());
    }

    private Location t(int i) {
        final int size = circleLocations.size();
        int val = next + i;
        while (val >= size) {
            val -= size;
        }
        return circleLocations.get(val);
    }

    @Override
    public void anim() {
        if (++next > circleLocations.size()) {
            next = 0;
        }
        final List<Packet<?>> packets = new ObjectArrayList<>();
        for (int i = 0; i < blockParts.size(); i++) {
            final BlockParts parts = blockParts.get(i);
            packets.addAll(parts.createMovePacket(t(i * SPLIT_COUNT)));
        }
        center.getNearbyPlayers(100, 50, 100).forEach(player ->
                packets.forEach(packet ->
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet)
                )
        );
    }

    @Override
    public void joinWorld(Player player) {
        blockParts.forEach(parts -> parts.sendSpawnPacket(player));
    }

    private enum Facing {
        EAST(true, true, true),
        SOUTH(false, true, false),
        WEST(false, false, true),
        NORTH(true, false, false);

        final boolean plus1;
        final boolean plus2;
        final boolean a;

        Facing(boolean plus1, boolean plus2, boolean xPlusA) {
            this.plus1 = plus1;
            this.plus2 = plus2;
            this.a = xPlusA;
        }
    }

    private static class BlockParts {
        private final AFBlock main;
        private final Object2IntMap<AFBlock> decorations = new Object2IntOpenHashMap<>();

        BlockParts(AFBlock main) {
            this.main = main;
        }

        static BlockParts random(Location pos) {
            final Material material;
            if (randomizer.nextDouble() < 0.3) {
                material = Material.PURPLE_GLAZED_TERRACOTTA;
            } else {
                material = Material.BLUE_GLAZED_TERRACOTTA;
            }
            final Directional blockData = (Directional) material.createBlockData();
            blockData.setFacing(faces[randomizer.nextInt(faces.length)]);
            final BlockParts parts = new BlockParts(new AFBlock(pos, blockData));
            if (randomizer.nextDouble() < 0.4) {
                dec(pos, parts, 1);
                dec(pos, parts, -1);
            }
            return parts;
        }

        static void dec(Location pos, BlockParts parts, int mode) {
            final int a = randomizer.nextInt(2) + 2;
            final int am = a * mode;
            parts.decorations.put(dec(pos, am, randomizer.nextBoolean() ? Material.CYAN_STAINED_GLASS_PANE.createBlockData() : Material.LIGHT_BLUE_STAINED_GLASS_PANE.createBlockData()), am);
            for (int i = 1; i < a; i++) {
                final int im = i * mode;
                parts.decorations.put(dec(pos, im, Material.CHAIN.createBlockData()), im);
            }
        }

        static AFBlock dec(Location pos, int y, BlockData blockData) {
            return new AFBlock(pos.clone().add(0, y, 0), blockData);
        }

        public void sendSpawnPacket(Player player) {
            main.sendSpawnPacket(player);
            decorations.keySet().forEach(afBlock -> afBlock.sendSpawnPacket(player));
        }

        public List<Packet<?>> createMovePacket(Location location) {
            final List<Packet<?>> packets = new ObjectArrayList<>();
            packets.add(main.createMovePacket(location));
            decorations.object2IntEntrySet().forEach(entry ->
                    packets.add(entry.getKey().createMovePacket(location.clone().add(0, entry.getIntValue(), 0)))
            );
            return packets;
        }

    }

}
