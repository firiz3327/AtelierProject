package net.firiz.atelierconstruction;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.firiz.atelierconstruction.entity.ArmorStandManagerV2;
import net.firiz.atelierconstruction.entity.version.AChair;
import net.firiz.atelierconstruction.entity.version.AFBlock;
import net.firiz.atelierconstruction.entity.CustomBlockManager;
import net.firiz.atelierconstruction.world.animation.AnimationManager;
import net.firiz.atelierconstruction.world.regulation.RegulationManager;
import net.kyori.adventure.text.Component;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class AListener implements Listener {

    private final Map<Player, LivingEntity> selectedEntity = new Object2ObjectOpenHashMap<>();
    private final RegulationManager regulation = RegulationManager.INSTANCE;

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
            if (e.getItem() == null) {
                if (player.getPassengers().isEmpty() && AChair.isChair(block)) {
                    new AChair(block.getLocation()).sitDown(player);
                }
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
                    } else if (entity instanceof final ItemFrame itemFrame) {
                        e.setCancelled(true);
                        itemFrame.setVisible(!itemFrame.isVisible());
                    }
                    break;
                case STICK:
                    if (entity instanceof final ItemFrame itemFrame) {
                        if (itemFrame.isFixed()) {
                            final Rotation rotation = itemFrame.getRotation();
                            itemFrame.setRotation(rotation.rotateClockwise());
                        }
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
                default:
                    break;
            }
        }
    }

    @EventHandler
    private void fadeBlock(final BlockFadeEvent e) {
        Material type = e.getBlock().getType();// ignored
        if (type == Material.TUBE_CORAL || type == Material.TUBE_CORAL_BLOCK || type == Material.TUBE_CORAL_FAN || type == Material.TUBE_CORAL_WALL_FAN || type == Material.BRAIN_CORAL || type == Material.BRAIN_CORAL_BLOCK || type == Material.BRAIN_CORAL_FAN || type == Material.BRAIN_CORAL_WALL_FAN || type == Material.BUBBLE_CORAL || type == Material.BUBBLE_CORAL_BLOCK || type == Material.BUBBLE_CORAL_FAN || type == Material.BUBBLE_CORAL_WALL_FAN || type == Material.FIRE_CORAL || type == Material.FIRE_CORAL_BLOCK || type == Material.FIRE_CORAL_FAN || type == Material.FIRE_CORAL_WALL_FAN || type == Material.HORN_CORAL || type == Material.HORN_CORAL_BLOCK || type == Material.HORN_CORAL_FAN || type == Material.HORN_CORAL_WALL_FAN) {
            final World world = e.getBlock().getWorld();
            if (Integer.valueOf(0).equals(world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED))) {
                e.setCancelled(true);
            }
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
            if (e.getPlayer().isOp()) {
                final ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName() && Component.text("fallingBlock").equals(meta.displayName())) {
                    e.setCancelled(true);
                    final Block block = e.getBlock();
                    new AFBlock(block.getLocation(), block.getBlockData()).sendSpawnPacket(e.getPlayer());
                    return;
                }
            }
            if (item.getType() == Material.FLOWER_POT) {
                e.setCancelled(true);
                CustomBlockManager.INSTANCE.flowerPot(e.getPlayer(), e.getBlock().getLocation(), item);
                return;
            }
        }
        regulation.onPlaceBlock(e);
    }

    @EventHandler
    private void hangingPlace(HangingPlaceEvent e) {
        if (e.getPlayer() != null && e.getEntity() instanceof ItemFrame) {
            final PlayerInventory inventory = e.getPlayer().getInventory();
            final ItemStack mainHand = inventory.getItemInMainHand();
            CustomBlockManager.INSTANCE.itemFrame((ItemFrame) e.getEntity(), mainHand);
        }
    }

    @EventHandler
    private void redStone(BlockRedstoneEvent e) {
        regulation.onRedstone(e);
    }

}
