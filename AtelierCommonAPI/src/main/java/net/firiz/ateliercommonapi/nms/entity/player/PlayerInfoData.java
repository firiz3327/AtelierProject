package net.firiz.ateliercommonapi.nms.entity.player;

import com.mojang.authlib.GameProfile;
import io.papermc.paper.adventure.AdventureComponent;
import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.world.level.EnumGamemode;
import org.bukkit.GameMode;

public record PlayerInfoData(GameProfile profile, int ping, GameMode gameMode, Component displayName) {

    public PacketPlayOutPlayerInfo.PlayerInfoData nms() {
        return new PacketPlayOutPlayerInfo.PlayerInfoData(profile, ping, nms(gameMode), new AdventureComponent(displayName));
    }

    private EnumGamemode nms(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> EnumGamemode.a;
            case CREATIVE -> EnumGamemode.b;
            case ADVENTURE -> EnumGamemode.c;
            case SPECTATOR -> EnumGamemode.d;
        };
    }

}
