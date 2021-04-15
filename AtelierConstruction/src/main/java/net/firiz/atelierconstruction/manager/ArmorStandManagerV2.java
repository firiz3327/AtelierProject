package net.firiz.atelierconstruction.manager;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.IntStream;

public enum ArmorStandManagerV2 {
    INSTANCE;

    private final Map<Player, ArmorStand> selectStandMap = new Object2ObjectOpenHashMap<>();

    public void selectStand(@NotNull final Player player, @NotNull final ArmorStand armorStand) {
        selectStandMap.put(player, armorStand);
        player.sendActionBar(Component.text("select to " + armorStand.getUniqueId()).color(NamedTextColor.GREEN));
    }

    public void showSelector(@NotNull final Player player) {
        final ArmorStand stand = selectStandMap.get(player);
        if (stand == null || stand.isDead()) {
            return;
        }

        final TextComponent.Builder builder = Component.text().color(NamedTextColor.GREEN);
        builder.append(Component.text("UUID: " + stand.getUniqueId()).color(NamedTextColor.RED)).append(Component.newline());
        o(builder);
        toggles(builder);
        player.sendMessage(builder.build());

        final TextComponent.Builder xyzBuilder = Component.text().color(NamedTextColor.GREEN);
        IntStream.range(0, 7).forEach(i -> {
            xyzBuilder.append(xyz(i));
            if (i != 6) {
                xyzBuilder.append(Component.newline());
            }
        });
        player.sendMessage(xyzBuilder.build());
    }

    private void o(TextComponent.Builder builder) {
        builder.append(Component.text("[Equip] ")
                .clickEvent(ClickEvent.runCommand("/asm equips"))
                .hoverEvent(HoverEvent.showText(Component.text("装備変更"))));
        builder.append(Component.text("[ResetPos]")
                .clickEvent(ClickEvent.runCommand("/asm resetpos"))
                .hoverEvent(HoverEvent.showText(Component.text("体の方向をリセット"))));
        builder.append(Component.newline());
    }

    private void toggles(TextComponent.Builder builder) {
        final String[] values = {"Invisible", "Invulnerable", "PersistenceRequired", "BasePlate", "Gravity", "ShowArms", "Small", "Marker"};
        final String[] descriptions = {"透明化", "不死身", "デスポーンしない", "プレート", "重力", "腕の表示", "縮小", "マーカー"};
        builder.append(Component.text("[Toggles] "));
        for (int i = 0; i < values.length; i++) {
            builder.append(
                    Component.text("{" + values[i] + "}")
                            .clickEvent(ClickEvent.runCommand(String.format("/asm toggles %d %s", i, values[i])))
                            .hoverEvent(HoverEvent.showText(Component.text(descriptions[i])))
            ).append(Component.text(" "));
        }
    }

    private Component xyz(int type) {
        final TextComponent.Builder builder = Component.text();
        String typeName;
        var rotate = false;
        switch (type) {
            case 1:
                typeName = "Head";
                break;
            case 2:
                typeName = "Body";
                break;
            case 3:
                typeName = "LeftArm";
                break;
            case 4:
                typeName = "RightArm";
                break;
            case 5:
                typeName = "LeftLeg";
                break;
            case 6:
                typeName = "RightLeg";
                break;
            default: {
                typeName = "Locs";
                rotate = true;
            }
        }
        builder.append(Component.text("[" + typeName + "] "));
        String[] xyz = rotate ? new String[]{"x", "y", "z", "r"} : new String[]{"x", "y", "z"};
        double[] values = {0.1, 1.0};
        for (String i : xyz) {
            builder.append(Component.text(i + ": "));
            IntStream.range(0, 2).forEach(j -> {
                boolean k = false;
                for (double l : values) {
                    if (k) {
                        builder.append(Component.text(" "));
                    }
                    k = true;
                    double v = (j == 0 ? 1.0 : -1.0) * l;
                    builder.append(Component.text(v).clickEvent(ClickEvent.runCommand(
                            String.format("/asm %s %f %s", i, v, typeName)
                    )).hoverEvent(Component.text(i + "pos " + v)));
                }
                builder.append(Component.text("  "));
            });
        }
        return builder.build();
    }

    public boolean command(Player sender, String... args) {
        final ArmorStand stand = selectStandMap.get(sender);
        if (stand == null || stand.isDead()) {
            return false;
        }
        final Location loc = stand.getLocation();
        switch (args[0]) {
            case "equips":
                final Inventory inv = Bukkit.createInventory(sender, 18, Component.text("装備変更"));
                final ItemStack[] contents = inv.getContents();
                for (int i = 0; i < contents.length; i++) {
                    final ItemStack item;
                    switch (i) {
                        case 0:
                            item = new ItemStack(Material.LEATHER_HELMET);
                            break;
                        case 1:
                            item = new ItemStack(Material.LEATHER_CHESTPLATE);
                            break;
                        case 2:
                            item = new ItemStack(Material.LEATHER_LEGGINGS);
                            break;
                        case 3:
                            item = new ItemStack(Material.LEATHER_BOOTS);
                            break;
                        case 4:
                            item = new ItemStack(Material.IRON_SWORD);
                            break;
                        case 5:
                            item = new ItemStack(Material.SHIELD);
                            break;
                        case 9:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getHelmet();
                            break;
                        case 10:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getChestplate();
                            break;
                        case 11:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getLeggings();
                            break;
                        case 12:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getBoots();
                            break;
                        case 13:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getItemInMainHand();
                            break;
                        case 14:
                            item = (stand.getEquipment() == null) ? null : stand.getEquipment().getItemInOffHand();
                            break;
                        default:
                            item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                            break;
                    }
                    if (item != null) {
                        contents[i] = item;
                    }
                }
                inv.setContents(contents);
                sender.openInventory(inv);
                break;
            case "toggles": {
                boolean change;
                switch (args[1]) {
                    case "0": {
                        stand.setVisible(!stand.isVisible());
                        change = stand.isVisible();
                        break;
                    }
                    case "1": {
                        stand.setInvulnerable(!stand.isInvulnerable());
                        change = stand.isInvulnerable();
                        break;
                    }
                    case "2": {
                        stand.setRemoveWhenFarAway(!stand.getRemoveWhenFarAway());
                        change = stand.getRemoveWhenFarAway();
                        break;
                    }
                    case "3": {
                        stand.setBasePlate(!stand.hasBasePlate());
                        change = stand.hasBasePlate();
                        break;
                    }
                    case "4": {
                        stand.setGravity(!stand.hasGravity());
                        change = stand.hasGravity();
                        break;
                    }
                    case "5": {
                        stand.setArms(!stand.hasArms());
                        change = stand.hasArms();
                        break;
                    }
                    case "6": {
                        stand.setSmall(!stand.isSmall());
                        change = stand.isSmall();
                        break;
                    }
                    case "7": {
                        stand.setMarker(!stand.isMarker());
                        change = stand.isMarker();
                        break;
                    }
                    default:
                        change = false;
                        break;
                }
                sender.sendActionBar(Component.text("Toggle " + args[2] + " is " + change).color(NamedTextColor.GREEN));
                break;
            }
            case "r":
                stand.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + Float.parseFloat(args[1]), loc.getPitch()));
                break;
            case "x":
                changePos(stand, Double.parseDouble(args[1]), 0.0, 0.0, args[2]);
                break;
            case "y":
                changePos(stand, 0.0, Double.parseDouble(args[1]), 0.0, args[2]);
                break;
            case "z":
                changePos(stand, 0.0, 0.0, Double.parseDouble(args[1]), args[2]);
                break;
            case "resetpos":
                stand.setHeadPose(EulerAngle.ZERO);
                stand.setBodyPose(EulerAngle.ZERO);
                stand.setRightArmPose(EulerAngle.ZERO);
                stand.setLeftArmPose(EulerAngle.ZERO);
                stand.setRightLegPose(EulerAngle.ZERO);
                stand.setLeftLegPose(EulerAngle.ZERO);
                break;
        }
        return true;
    }

    private void changePos(ArmorStand stand, double x, double y, double z, String type) {
        switch (type) {
            case "Head":
                stand.setHeadPose(stand.getHeadPose().add(x, y, z));
                break;
            case "Body":
                stand.setBodyPose(stand.getBodyPose().add(x, y, z));
                break;
            case "LeftArm":
                stand.setLeftArmPose(stand.getLeftArmPose().add(x, y, z));
                break;
            case "RightArm":
                stand.setRightArmPose(stand.getRightArmPose().add(x, y, z));
                break;
            case "LeftLeg":
                stand.setLeftLegPose(stand.getLeftLegPose().add(x, y, z));
                break;
            case "RightLeg":
                stand.setRightLegPose(stand.getRightLegPose().add(x, y, z));
                break;
            default: {
                final Location loc = stand.getLocation();
                stand.teleport(new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z, loc.getYaw(), loc.getPitch()));
                break;
            }
        }
    }

    public void equip(Player player, Inventory inv) {
        final ArmorStand stand = selectStandMap.get(player);
        if (stand == null || stand.isDead()) {
            return;
        }
        final EntityEquipment equipment = stand.getEquipment();
        if (equipment != null) {
            equipment.setHelmet(inv.getContents()[9]);
            equipment.setChestplate(inv.getContents()[10]);
            equipment.setLeggings(inv.getContents()[11]);
            equipment.setBoots(inv.getContents()[12]);
            equipment.setItemInMainHand(inv.getContents()[13]);
            equipment.setItemInOffHand(inv.getContents()[14]);
        }
    }

}
