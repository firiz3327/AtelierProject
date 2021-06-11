package net.firiz.ateliercommonapi.adventure.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Text extends TextComponentWrapper {

    private TextComponentWrapper lastComponent;

    public Text() {
        this(true);
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

    public static Text of(String name) {
        return new Text(name);
    }

    public static Text of(String name, TextColor color) {
        return of(name).color(color);
    }

    public static Text itemName(String name) {
        return new Text(name, true);
    }

    public static Text itemName(String name, TextColor color) {
        return itemName(name).color(color);
    }

    public boolean plainStartsWith(Component prefixComponent) {
        final String prefix = PlainComponentSerializer.plain().serialize(prefixComponent);
        return plainStartsWith(prefix);
    }

    public boolean plainStartsWith(String prefix) {
        return PlainComponentSerializer.plain().serialize(this).startsWith(prefix);
    }

    public boolean plainEndsWith(Component suffixComponent) {
        final String suffix = PlainComponentSerializer.plain().serialize(suffixComponent);
        return plainStartsWith(suffix);
    }

    public boolean plainEndsWith(String suffix) {
        return PlainComponentSerializer.plain().serialize(this).endsWith(suffix);
    }

    public static boolean plainStartsWith(Component component, Component prefixComponent) {
        final String prefix = PlainComponentSerializer.plain().serialize(prefixComponent);
        return plainStartsWith(component, prefix);
    }

    public static boolean plainStartsWith(Component component, String prefix) {
        return PlainComponentSerializer.plain().serialize(component).startsWith(prefix);
    }

    public static boolean plainEndsWith(Component component, Component suffixComponent) {
        final String suffix = PlainComponentSerializer.plain().serialize(suffixComponent);
        return plainStartsWith(component, suffix);
    }

    public static boolean plainEndsWith(Component component, String suffix) {
        return PlainComponentSerializer.plain().serialize(component).endsWith(suffix);
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
    public @NonNull Text hoverEvent(@org.checkerframework.checker.nullness.qual.Nullable HoverEventSource<?> event) {
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
    public @NonNull Text append(@NonNull Component component) {
        if (component instanceof TextComponent) {
            final TextComponentWrapper c = new TextComponentWrapper((TextComponent) component);
            super.append(c);
            this.lastComponent = c;
            return this;
        }
        throw new IllegalArgumentException("Only TextComponent is supported.");
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
