package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Text extends TextComponentWrapper {

    private ComponentWrapper<?> lastComponent;

    public Text() {
        this(true);
    }

    public Text(Component component) {
        this(component, true);
    }

    public Text(TextComponent component) {
        this(component, true);
    }

    public Text(@Nullable final String text) {
        this(text, true);
    }

    public Text(final char value) {
        this(value, true);
    }

    public Text(final byte value) {
        this(value, true);
    }

    public Text(final short value) {
        this(value, true);
    }

    public Text(final int value) {
        this(value, true);
    }

    public Text(final long value) {
        this(value, true);
    }

    public Text(final float value) {
        this(value, true);
    }

    public Text(final double value) {
        this(value, true);
    }

    public Text(final boolean autoRemoveItalic) {
        super(Component.text(""));
        initialize(autoRemoveItalic);
    }

    public Text(@Nullable final Component component, final boolean autoRemoveItalic) {
        super(Component.empty());
        initialize(autoRemoveItalic);
        if (component != null) {
            append(component);
        }
    }

    public Text(@Nullable final TextComponent component, final boolean autoRemoveItalic) {
        super(component);
        initialize(autoRemoveItalic);
    }

    public Text(@Nullable final String text, final boolean autoRemoveItalic) {
        super(Component.text(Objects.requireNonNullElse(text, "")));
        initialize(autoRemoveItalic);
    }

    public Text(final char value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final byte value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final short value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final int value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final long value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final float value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    public Text(final double value, final boolean autoRemoveItalic) {
        super(Component.text(value));
        initialize(autoRemoveItalic);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initialize(boolean autoRemoveItalic) {
        this.lastComponent = this;
        if (autoRemoveItalic) {
            super.decoration(TextDecoration.ITALIC, false);
        }
    }

    public static Text empty() {
        return new Text();
    }

    public static Text of(String text) {
        return new Text(text);
    }

    public static Text of(String text, TextColor color) {
        return of(text).color(color);
    }

    public static Text itemName(String name) {
        return new Text(name, true);
    }

    public static Text itemName(String name, TextColor color) {
        return itemName(name).color(color);
    }

    public static Text translateColor(String text) {
        return new Text(LegacyComponentSerializer.legacyAmpersand().deserialize(text));
    }

    public Component get(int index) {
        return children().get(index);
    }

    public String plain() {
        return plain(this);
    }

    public static String plain(Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }

    public boolean plainStartsWith(Component prefixComponent) {
        return plainStartsWith(plain(prefixComponent));
    }

    public boolean plainStartsWith(String prefix) {
        return plain().startsWith(prefix);
    }

    public boolean plainEndsWith(Component suffixComponent) {
        return plainStartsWith(plain(suffixComponent));
    }

    public boolean plainEndsWith(String suffix) {
        return plain().endsWith(suffix);
    }

    public static boolean plainStartsWith(Component component, Component prefixComponent) {
        return plainStartsWith(component, plain(prefixComponent));
    }

    public static boolean plainStartsWith(Component component, String prefix) {
        return plain(component).startsWith(prefix);
    }

    public static boolean plainEndsWith(Component component, Component suffixComponent) {
        return plainStartsWith(component, plain(suffixComponent));
    }

    public static boolean plainEndsWith(Component component, String suffix) {
        return plain(component).endsWith(suffix);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public @NotNull Text clickEvent(ClickEvent event) {
        if (lastComponent == this) {
            super.clickEvent(event);
        } else {
            lastComponent.clickEvent(event);
        }
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public @NonNull @NotNull Text hoverEvent(@org.checkerframework.checker.nullness.qual.Nullable HoverEventSource<?> event) {
        if (lastComponent == this) {
            super.hoverEvent(event);
        } else {
            lastComponent.hoverEvent(event);
        }
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public @NotNull Text color(@Nullable final TextColor color) {
        Objects.requireNonNull(color, "color is null.");
        if (lastComponent == this) {
            super.color(color);
        } else {
            lastComponent.color(color);
        }
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text deco(@NotNull final TextDecoration decoration) {
        Objects.requireNonNull(decoration, "decoration is null.");
        if (lastComponent == this) {
            super.decorate(decoration);
        } else {
            lastComponent.decorate(decoration);
        }
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text deco(@NotNull final TextDecoration decoration, boolean available) {
        Objects.requireNonNull(decoration, "decoration is null.");
        if (lastComponent == this) {
            super.decoration(decoration, available);
        } else {
            lastComponent.decoration(decoration, available);
        }
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public @NotNull Text style(@NotNull final Style style) {
        if (lastComponent == this) {
            super.style(style);
        } else {
            lastComponent.style(style);
        }
        return this;
    }

    @Override
    public @NonNull @NotNull Text append(@NonNull Component component) {
        final ComponentWrapper<?> c = ComponentWrapper.wrap(component);
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(@Nullable final String text) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final char value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final byte value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final short value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final int value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final long value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final float value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Text append(final double value) {
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        super.append(c);
        this.lastComponent = c;
        return this;
    }

}
