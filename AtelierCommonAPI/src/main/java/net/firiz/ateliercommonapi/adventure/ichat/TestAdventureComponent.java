package net.firiz.ateliercommonapi.adventure.ichat;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.network.chat.*;
import net.minecraft.resources.MinecraftKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TestAdventureComponent {

    private final Component wrapped;
    private IChatBaseComponent component;

    private TestAdventureComponent(Component wrapped) {
        this.wrapped = wrapped;
    }

    public static IChatBaseComponent of(Component wrapped) {
        return new TestAdventureComponent(wrapped).convert();
    }

    public IChatBaseComponent convert() {
        this.component = convert(wrapped);
        return this.component;
    }

    public Component getWrapped() {
        return wrapped;
    }

    public IChatBaseComponent getComponent() {
        return component;
    }

    private IChatBaseComponent convert(Component adventureComponent) {
        final IChatBaseComponent minecraftComponent = text(adventureComponent);
        final List<IChatBaseComponent> siblings = minecraftComponent.getSiblings();
        adventureComponent.children().forEach(child -> siblings.add(convert(child)));
        return minecraftComponent;
    }

    private ChatComponentText text(Component component) {
        final ChatComponentText text = new ChatComponentText(((TextComponent) wrapped).content());
        text.setChatModifier(chatModifier(component));
        return text;
    }

    private ChatModifier chatModifier(Component component) {
        final Style style = component.style();
        final ChatModifier chatModifier = ChatModifier.a.setColor(color(style.color()));
        style.decorations().forEach((decoration, state) -> {
            switch (decoration) {
                case BOLD -> chatModifier.setBold(convertState(state));
                case ITALIC -> chatModifier.setItalic(convertState(state));
                case OBFUSCATED -> chatModifier.setRandom(convertState(state)); // <- 違うかも？ &k のやつ
                case UNDERLINED -> chatModifier.setUnderline(convertState(state));
                case STRIKETHROUGH -> chatModifier.setStrikethrough(convertState(state));
            }
        });
        Optional.ofNullable(style.clickEvent()).ifPresent(event -> chatModifier.setChatClickable(chatClickable(event)));
        // hoverEvent
        chatModifier.a(font(style.font()));
        return chatModifier;
    }

    @Nullable
    private ChatHexColor color(TextColor color) {
        return color == null ? null : ChatHexColor.a(color.value());
    }

    @Nullable
    private Boolean convertState(TextDecoration.State state) {
        return switch (state) {
            case TRUE -> true;
            case FALSE -> false;
            default -> null;
        };
    }

    @NotNull
    private ChatClickable chatClickable(ClickEvent clickEvent) {
        final ChatClickable.EnumClickAction action = switch (clickEvent.action()) {
            case OPEN_URL -> ChatClickable.EnumClickAction.a;
            case OPEN_FILE -> ChatClickable.EnumClickAction.b;
            case RUN_COMMAND -> ChatClickable.EnumClickAction.c;
            case SUGGEST_COMMAND -> ChatClickable.EnumClickAction.d;
            case CHANGE_PAGE -> ChatClickable.EnumClickAction.e;
            case COPY_TO_CLIPBOARD -> ChatClickable.EnumClickAction.f;
        };
        return new ChatClickable(action, clickEvent.value());
    }

    @Nullable
    private ChatHoverable chatHoverable(HoverEvent<?> hoverEvent) {
        return null;
    }

    @Nullable
    private MinecraftKey font(Key key) {
        return key == null ? null : new MinecraftKey(key.namespace(), key.value());
    }
}
