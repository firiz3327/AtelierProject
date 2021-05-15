package net.firiz.ateliercommonapi.adventure.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Lore extends ObjectArrayList<Component> {

    private static final String NULL_COMPONENT_ERR = "component is null.";
    private final boolean autoRemoveItalic;
    private transient TextComponentWrapper component;

    public Lore() {
        this.autoRemoveItalic = false;
    }

    public Lore(final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
    }

    public Lore(@Nullable final String text) {
        this.autoRemoveItalic = false;
        add(text);
    }

    public Lore(final char value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final byte value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final short value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final int value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final long value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final float value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(final double value) {
        this.autoRemoveItalic = false;
        add(value);
    }

    public Lore(@Nullable final String text, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(text);
    }

    public Lore(final char value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final byte value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final short value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final int value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final long value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final float value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore(final double value, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        add(value);
    }

    public Lore color(@NotNull final C color) {
        return color(color.getColor());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public @NotNull Lore color(@Nullable final TextColor color) {
        Objects.requireNonNull(color, "color is null.");
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        component.color(color);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore deco(@NotNull final TextDecoration decoration) {
        Objects.requireNonNull(decoration, "decoration is null.");
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        component.decorate(decoration);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore deco(@NotNull final TextDecoration decoration, boolean available) {
        Objects.requireNonNull(decoration, "decoration is null.");
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        component.decoration(decoration, available);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore style(@NotNull final Style style) {
        component.style(style);
        return this;
    }

    public Lore nextLine() {
        return add("");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public boolean add(Component component) {
        if (component instanceof TextComponent) {
            this.component = new TextComponentWrapper((TextComponent) component);
            if (autoRemoveItalic) {
                this.component.decoration(TextDecoration.ITALIC, false);
            }
            return super.add(this.component);
        }
        throw new IllegalArgumentException("component isn't TextComponent class.");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore add(@Nullable final String text) {
        component = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        if (autoRemoveItalic) {
            component.decoration(TextDecoration.ITALIC, false);
        }
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(@Nullable final String text) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final char value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final char value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final byte value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final byte value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final short value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final short value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final int value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final int value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final long value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final long value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final float value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final float value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final double value) {
        component = new TextComponentWrapper(Component.text(value));
        add(component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final double value) {
        Objects.requireNonNull(component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        component.append(c);
        this.component = c;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Lore that = (Lore) o;
        return Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), component);
    }

}
