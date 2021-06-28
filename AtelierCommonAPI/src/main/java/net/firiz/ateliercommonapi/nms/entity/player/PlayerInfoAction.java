package net.firiz.ateliercommonapi.nms.entity.player;

import com.google.common.collect.Lists;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.level.EntityPlayer;

import java.lang.reflect.Field;
import java.util.List;

public enum PlayerInfoAction {
    ADD_PLAYER(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a),
    UPDATE_GAME_MODE(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b),
    UPDATE_PING(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.c),
    UPDATE_DISPLAY_NAME(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.d),
    REMOVE_PLAYER(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e);

    private static final Field bField;
    private final PacketPlayOutPlayerInfo.EnumPlayerInfoAction infoAction;

    static {
        try {
            bField = PacketPlayOutPlayerInfo.class.getDeclaredField("b");
            bField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("not found PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b field.");
        }
    }

    PlayerInfoAction(PacketPlayOutPlayerInfo.EnumPlayerInfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public PacketPlayOutPlayerInfo.EnumPlayerInfoAction nms() {
        return infoAction;
    }

    public PacketPlayOutPlayerInfo compile(EntityPlayer... players) {
        return new PacketPlayOutPlayerInfo(infoAction, players);
    }

    public PacketPlayOutPlayerInfo compile(PlayerInfoData... players) {
        final PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(infoAction);
        try {
            final List<PacketPlayOutPlayerInfo.PlayerInfoData> dataList = Lists.newArrayListWithCapacity(players.length);
            for (final PlayerInfoData data : players) {
                dataList.add(data.nms());
            }
            bField.set(packet, dataList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return packet;
    }
}
