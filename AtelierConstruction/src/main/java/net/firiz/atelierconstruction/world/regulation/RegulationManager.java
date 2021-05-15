package net.firiz.atelierconstruction.world.regulation;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.firiz.ateliercommonapi.loop.LoopManager;
import net.firiz.atelierconstruction.constants.Message;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.function.Predicate;

public enum RegulationManager {
    INSTANCE;

    private final Object2ObjectMap<Chunk, R> redStoneCh = new Object2ObjectOpenHashMap<>();
    private final Predicate<Block> redstonePredicate = block -> isRedStone(block.getType());

    private final int blockSearchRadius = 10;

    private int time;
    private final int redstoneBlockCount = 30;
    private final String redstoneMessage = String.format(Message.REDSTONE_REGULATION_MESSAGE.toString(), redstoneBlockCount);

    public void init() {
        LoopManager.INSTANCE.addSeconds(() -> {
            time++;
            if (time >= 5) {
                time = 0;
                redStoneCh.clear();
            }
        });
    }

    public void onPlaceBlock(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlockPlaced();
        if (isRedStone(block.getType()) && nearbyBlock(block, redstoneBlockCount, redstonePredicate)) {
            player.sendMessage(redstoneMessage);
            event.setCancelled(true);
            event.setBuild(false);
        }
    }

    public void onRedstone(BlockRedstoneEvent event) {
        final Chunk chunk = event.getBlock().getChunk();
        final long timeStamp = System.currentTimeMillis();
        if (redStoneCh.containsKey(chunk)) {
            final R r = redStoneCh.get(chunk);
            if (timeStamp - r.timeStamp > 100L) {
                r.value += 1;
                r.timeStamp = timeStamp;
                if (r.value >= 10) {
                    final Block center = event.getBlock();
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            for (int z = -1; z <= 1; z++) {
                                final Block b = center.getRelative(x, y, z);
                                if (isRedStone(b.getType())) {
                                    b.setType(Material.AIR);
                                }
                            }
                        }
                    }
                    redStoneCh.remove(chunk);
                }
            }
        } else {
            redStoneCh.put(chunk, new R(timeStamp));
        }
    }

    private boolean nearbyBlock(Block block, int max, Predicate<Block> predicate) {
        int count = 0;
        for (int x = -(blockSearchRadius); x <= blockSearchRadius; x++) {
            for (int y = -(blockSearchRadius); y <= blockSearchRadius; y++) {
                for (int z = -(blockSearchRadius); z <= blockSearchRadius; z++) {
                    if (predicate.test(block.getRelative(x, y, z))) {
                        count++;
                        if (max <= count) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isRedStone(Material type) {
        switch (type) {
            case STICKY_PISTON:
            case PISTON:
            case REDSTONE_TORCH:
            case REDSTONE_WALL_TORCH:
            case REDSTONE_LAMP:
            case TRIPWIRE_HOOK:
            case DAYLIGHT_DETECTOR:
            case REDSTONE_BLOCK:
            case OBSERVER:
            case REPEATER:
            case COMPOSTER:
            case REDSTONE_WIRE:
                return true;
            default:
                return false;
        }
    }

    private static class R {
        long timeStamp;
        int value;

        public R(long timeStamp) {
            this.timeStamp = timeStamp;
            this.value = 0;
        }
    }

}
