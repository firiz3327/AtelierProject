package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public enum C {
    BLACK(NamedTextColor.BLACK),
    DARK_BLUE(NamedTextColor.DARK_BLUE),
    DARK_GREEN(NamedTextColor.DARK_GREEN),
    DARK_AQUA(NamedTextColor.DARK_AQUA),
    DARK_RED(NamedTextColor.DARK_RED),
    DARK_PURPLE(NamedTextColor.DARK_PURPLE),
    GOLD(NamedTextColor.GOLD),
    GRAY(NamedTextColor.GRAY),
    DARK_GRAY(NamedTextColor.DARK_GRAY),
    BLUE(NamedTextColor.BLUE),
    GREEN(NamedTextColor.GREEN),
    AQUA(NamedTextColor.AQUA),
    RED(NamedTextColor.RED),
    LIGHT_PURPLE(NamedTextColor.LIGHT_PURPLE),
    YELLOW(NamedTextColor.YELLOW),
    WHITE(NamedTextColor.WHITE),
    MINECOIN_GOLD(14538245), // [&g] Bedrock Edition
    // https://flatuicolors.com/palette/defo
    FLAT_LIGHT_GREEN1(1752220),
    FLAT_LIGHT_GREEN2(1482885),
    FLAT_YELLOW1(15844367),
    FLAT_YELLOW2(15965202),
    FLAT_GREEN1(3066993),
    FLAT_GREEN2(2600544),
    FLAT_ORANGE1(15105570),
    FLAT_ORANGE2(13849600),
    FLAT_BLUE1(3447003),
    FLAT_BLUE2(2719929),
    FLAT_RED1(15158332),
    FLAT_RED2(12597547),
    FLAT_PURPLE1(10181046),
    FLAT_PURPLE2(9323693),
    FLAT_SILVER1(15528177),
    FLAT_SILVER2(12436423),
    FLAT_NAVY_BLUE1(3426654),
    FLAT_NAVY_BLUE2(2899536),
    FLAT_GRAY1(9807270),
    FLAT_GRAY2(8359053);

    private final TextColor color;

    C(TextColor color) {
        this.color = color;
    }

    C(int hex) {
        this.color = TextColor.color(hex);
    }

    public TextColor getColor() {
        return color;
    }
}
