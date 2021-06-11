package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public final class C implements TextColor {
    public static final C BLACK = new C(NamedTextColor.BLACK);
    public static final C DARK_BLUE = new C(NamedTextColor.DARK_BLUE);
    public static final C DARK_GREEN = new C(NamedTextColor.DARK_GREEN);
    public static final C DARK_AQUA = new C(NamedTextColor.DARK_AQUA);
    public static final C DARK_RED = new C(NamedTextColor.DARK_RED);
    public static final C DARK_PURPLE = new C(NamedTextColor.DARK_PURPLE);
    public static final C GOLD = new C(NamedTextColor.GOLD);
    public static final C GRAY = new C(NamedTextColor.GRAY);
    public static final C DARK_GRAY = new C(NamedTextColor.DARK_GRAY);
    public static final C BLUE = new C(NamedTextColor.BLUE);
    public static final C GREEN = new C(NamedTextColor.GREEN);
    public static final C AQUA = new C(NamedTextColor.AQUA);
    public static final C RED = new C(NamedTextColor.RED);
    public static final C LIGHT_PURPLE = new C(NamedTextColor.LIGHT_PURPLE);
    public static final C YELLOW = new C(NamedTextColor.YELLOW);
    public static final C WHITE = new C(NamedTextColor.WHITE);
    public static final C MINECOIN_GOLD = new C(14538245); // [&g] Bedrock Edition
    // https://flatuicolors.com/palette/defo
    public static final C FLAT_LIGHT_GREEN1 = new C(1752220);
    public static final C FLAT_LIGHT_GREEN2 = new C(1482885);
    public static final C FLAT_YELLOW1 = new C(15844367);
    public static final C FLAT_YELLOW2 = new C(15965202);
    public static final C FLAT_GREEN1 = new C(3066993);
    public static final C FLAT_GREEN2 = new C(2600544);
    public static final C FLAT_ORANGE1 = new C(15105570);
    public static final C FLAT_ORANGE2 = new C(13849600);
    public static final C FLAT_BLUE1 = new C(3447003);
    public static final C FLAT_BLUE2 = new C(2719929);
    public static final C FLAT_RED1 = new C(15158332);
    public static final C FLAT_RED2 = new C(12597547);
    public static final C FLAT_PURPLE1 = new C(10181046);
    public static final C FLAT_PURPLE2 = new C(9323693);
    public static final C FLAT_SILVER1 = new C(15528177);
    public static final C FLAT_SILVER2 = new C(12436423);
    public static final C FLAT_NAVY_BLUE1 = new C(3426654);
    public static final C FLAT_NAVY_BLUE2 = new C(2899536);
    public static final C FLAT_GRAY1 = new C(9807270);
    public static final C FLAT_GRAY2 = new C(8359053);

    private final int value;

    C(TextColor color) {
        this.value = color.value();
    }

    C(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
}
