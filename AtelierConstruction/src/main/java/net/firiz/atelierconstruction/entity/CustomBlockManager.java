package net.firiz.atelierconstruction.entity;

import net.firiz.atelierconstruction.entity.version.FixedItemFrame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum CustomBlockManager {
    INSTANCE;

    // armorStand 350 -> 110
    // item_frame 350 -> 180

    @Nullable
    private Type getType(ItemStack item) {
        if (item.hasItemMeta()) {
            final ItemMeta meta = item.getItemMeta();
            if (meta.hasCustomModelData()) {
                return Type.search(item.getType(), meta.getCustomModelData());
            }
        }
        return null;
    }

    public void open(Player player) {
        final Inventory inv = Bukkit.createInventory(player, 54);
        for (Type type : CustomBlockManager.Type.values()) {
            inv.addItem(type.item());
        }
        player.openInventory(inv);
    }

    public void itemFrame(@NotNull ItemFrame frame, @Nullable ItemStack item) {
        if (item == null) {
            return;
        }
        final Type type = getType(item);
        if (type != null) {
            frame.setVisible(false);
            frame.setFixed(true);
            frame.setItem(type.item());
        }
    }

    public void flowerPot(Player player, Location location, ItemStack item) {
        if (player == null || item == null) {
            return;
        }
        final Type type = getType(item);
        if (type != null) {
            FixedItemFrame.spawn(location, BlockFace.DOWN, itemFrame -> {
                itemFrame.setVisible(false);
                itemFrame.setItem(type.item());
            });
        }
    }

    public enum Type {
        OAK(Material.ITEM_FRAME, 100),
        SPRUCE(Material.ITEM_FRAME, 101),
        BIRCH(Material.ITEM_FRAME, 102),
        JUNGLE(Material.ITEM_FRAME, 103),
        ACACIA(Material.ITEM_FRAME, 104),
        DARK_OAK(Material.ITEM_FRAME, 105),
        CRIMSON(Material.ITEM_FRAME, 106),
        WARPED(Material.ITEM_FRAME, 107),
        STONE(Material.ITEM_FRAME, 108),
        SMOOTH_STONE(Material.ITEM_FRAME, 109),
        BRICKS(Material.ITEM_FRAME, 110),
        STONE_BRICKS(Material.ITEM_FRAME, 111),
        TABLE_OAK(Material.FLOWER_POT, 100),
        TABLE_SPRUCE(Material.FLOWER_POT, 101),
        TABLE_OAK_DOUBLE(Material.FLOWER_POT, 102),
        TABLE_SPRUCE_DOUBLE(Material.FLOWER_POT, 103),
        CHAIR_OAK(Material.FLOWER_POT, 104),
        CHAIR_SPRUCE(Material.FLOWER_POT, 105),
        CHAIR_OAK_DOUBLE(Material.FLOWER_POT, 106),
        CHAIR_SPRUCE_DOUBLE(Material.FLOWER_POT, 107),
        ;

        private final Material material;
        private final int data;

        Type(Material material, int data) {
            this.material = material;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public static Type search(Material material, int data) {
            for (Type type : values()) {
                if (type.material == material && type.data == data) {
                    return type;
                }
            }
            return null;
        }

        public ItemStack item() {
            final ItemStack item = new ItemStack(material, 1);
            final ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(getData());
            item.setItemMeta(meta);
            return item;
        }

    }

}
