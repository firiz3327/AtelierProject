package net.firiz.ateliercommonapi.nms.packet;

import io.papermc.paper.adventure.AdventureComponent;
import net.firiz.ateliercommonapi.MinecraftVersion;
import net.firiz.ateliercommonapi.nms.MinecraftConverter;
import net.kyori.adventure.text.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
import net.minecraft.network.protocol.game.PacketPlayOutSetSlot;
import net.minecraft.network.protocol.game.PacketPlayOutWindowItems;
import net.minecraft.world.inventory.Container;
import net.minecraft.world.inventory.Containers;
import net.minecraft.world.item.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.EnumSet;

@MinecraftVersion("1.17")
public final class InventoryPacket {

    private InventoryPacket() {
    }

    public static void update(final Player player, final Component title, final InventoryPacketType type) {
        final Container bV = MinecraftConverter.convertNMS(player).bV; // container
        final int windowId = bV.j; // EntityHuman.bV.windowId
        final PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(
                windowId,
                type.getContainer(player.getOpenInventory().getTopInventory().getSize()),
                new AdventureComponent(title)
        );
        PacketUtils.sendPacket(player, packet);
        updateInventory(player, windowId, bV, bV.c());
    }

    /**
     * 1.16のEntityHuman#updateInventoryを1.17用に作成
     */
    private static void updateInventory(Player player, int windowId, Container container, NonNullList<ItemStack> nonNullList) {
        PacketUtils.sendPacket(player, new PacketPlayOutWindowItems(windowId, nonNullList));
        PacketUtils.sendPacket(player, new PacketPlayOutSetSlot(-1, -1, container.getCarried())); // 1.17でcarriedがplayerInventoryからcontainerへ移動している
        if (EnumSet.of(InventoryType.CRAFTING, InventoryType.WORKBENCH).contains(container.getBukkitView().getType())) {
            PacketUtils.sendPacket(player, new PacketPlayOutSetSlot(windowId, 0, container.getSlot(0).getItem()));
        }
    }

    public enum InventoryPacketType {
        CHEST(null),
        DISPENSER(Containers.g),
        DROPPER(Containers.g),
        ANVIL(Containers.h),
        BEACON(Containers.i),
        BLAST_FURNACE(Containers.j),
        BREWING_STAND(Containers.k),
        CRAFTING(Containers.l),
        ENCHANTMENT(Containers.m),
        FURNACE(Containers.n),
        GRIND_STONE(Containers.o),
        HOPPER(Containers.p),
        LECTERN(Containers.q),
        LOOM(Containers.r),
        MERCHANT(Containers.s),
        SHULKER_BOX(Containers.t),
        SMITHING(Containers.u),
        SMOKER(Containers.v),
        CARTOGRAPHY_TABLE(Containers.w),
        STONE_CUTTER(Containers.x);

        private final Containers<?> container;

        InventoryPacketType(Containers<?> container) {
            this.container = container;
        }

        public final Containers<?> getContainer(int size) {
            if (this == CHEST) {
                return switch (size) {
                    case 9 -> Containers.a;
                    case 18 -> Containers.b;
                    case 27 -> Containers.c;
                    case 36 -> Containers.d;
                    case 45 -> Containers.e;
                    case 54 -> Containers.f;
                    default -> throw new IllegalArgumentException(size + " is unsupported size.");
                };
            }
            return container;
        }

    }
}
