package net.firiz.ateliercommonapi.nms.item;

import net.minecraft.core.IRegistry;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;

public class NMSItemStack {

    private final ItemStack itemStack;

    public NMSItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static NMSItemStack convert(org.bukkit.inventory.ItemStack itemStack) {
        return new NMSItemStack(CraftItemStack.asNMSCopy(itemStack));
    }

    public org.bukkit.inventory.ItemStack itemStack() {
        return itemStack.asBukkitCopy();
    }

    public ItemStack nms() {
        return itemStack;
    }

    public String localizeId() {
        return itemStack.getItem().getName();
    }

    public String minecraftId() {
        // ItemStack.class [NBTTagCompound save(NBTTagCompound nbt)]
        return IRegistry.Z.getKey(itemStack.getItem()).toString();
    }

    public static String getLocalizeId(org.bukkit.inventory.ItemStack itemStack) {
        return NMSItemStack.convert(itemStack).localizeId();
    }

    public static String getMinecraftId(org.bukkit.inventory.ItemStack itemStack) {
        return NMSItemStack.convert(itemStack).minecraftId();
    }

}
