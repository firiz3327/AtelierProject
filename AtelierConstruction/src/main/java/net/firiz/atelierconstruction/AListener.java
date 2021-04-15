package net.firiz.atelierconstruction;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.firiz.atelierconstruction.manager.ArmorStandManagerV2;
import net.firiz.atelierconstruction.version.AChair;
import net.firiz.atelierconstruction.version.AFBlock;
import net.firiz.atelierconstruction.world.animation.AnimationManager;
import net.kyori.adventure.text.Component;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class AListener implements Listener {

    private final Map<Player, LivingEntity> selectedEntity = new Object2ObjectOpenHashMap<>();

    @EventHandler
    private void changeWorld(final PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world")) {
            AnimationManager.INSTANCE.joinWorld(e.getPlayer());
        }
    }

    @EventHandler
    private void joinWorld(final PlayerJoinEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world")) {
            AnimationManager.INSTANCE.joinWorld(e.getPlayer());
        }
    }

    @EventHandler
    private void interactBlock(final PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final Block block = e.getClickedBlock();
        if (block != null && e.getHand() == EquipmentSlot.HAND && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() == null && player.getPassengers().isEmpty() && AChair.isChair(block)) {
                new AChair(block.getLocation()).sitDown(player);
            }
        }
    }

    @EventHandler
    private void interactEntity(final PlayerInteractAtEntityEvent e) {
        final Player player = e.getPlayer();
        final Entity entity = e.getRightClicked();
        if (player.isOp() && e.getHand() == EquipmentSlot.HAND) {
            switch (player.getInventory().getItemInMainHand().getType()) {
                case FLINT:
                    if (entity instanceof ArmorStand) {
                        e.setCancelled(true);
                        ArmorStandManagerV2.INSTANCE.selectStand(player, (ArmorStand) entity);
                        ArmorStandManagerV2.INSTANCE.showSelector(player);
                    }
                    break;
                case DEBUG_STICK:
                    if (entity instanceof LivingEntity) {
                        if (selectedEntity.containsKey(player)) {
                            player.sendMessage("add passenger");
                            selectedEntity.remove(player).addPassenger(entity);
                        } else {
                            player.sendMessage("selected one");
                            selectedEntity.put(player, (LivingEntity) entity);
                        }
                    }
                    break;
                default: // ignored
                    break;
            }
        }
    }

    @EventHandler
    private void fadeBlock(final BlockFadeEvent e) {
        switch (e.getBlock().getType()) {
            case TUBE_CORAL:
            case TUBE_CORAL_BLOCK:
            case TUBE_CORAL_FAN:
            case TUBE_CORAL_WALL_FAN:
            case BRAIN_CORAL:
            case BRAIN_CORAL_BLOCK:
            case BRAIN_CORAL_FAN:
            case BRAIN_CORAL_WALL_FAN:
            case BUBBLE_CORAL:
            case BUBBLE_CORAL_BLOCK:
            case BUBBLE_CORAL_FAN:
            case BUBBLE_CORAL_WALL_FAN:
            case FIRE_CORAL:
            case FIRE_CORAL_BLOCK:
            case FIRE_CORAL_FAN:
            case FIRE_CORAL_WALL_FAN:
            case HORN_CORAL:
            case HORN_CORAL_BLOCK:
            case HORN_CORAL_FAN:
            case HORN_CORAL_WALL_FAN:
                final World world = e.getBlock().getWorld();
                if (Integer.valueOf(0).equals(world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED))) {
                    e.setCancelled(true);
                }
                break;
            default: // ignored
                break;
        }
    }

    @EventHandler
    private void close(InventoryCloseEvent e) {
        if (e.getView().title().equals(Component.text("装備変更"))) {
            ArmorStandManagerV2.INSTANCE.equip((Player) e.getPlayer(), e.getInventory());
        }
    }

    @EventHandler
    private void place(BlockPlaceEvent e) {
        final ItemStack item = e.getItemInHand();
        if (item.hasItemMeta()) {
            final ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName() && Component.text("fallingBlock").equals(meta.displayName())) {
                e.setCancelled(true);
                final Block block = e.getBlock();
                new AFBlock(block.getLocation(), block.getBlockData()).sendSpawnPacket(e.getPlayer());
            }
        }
    }

}
