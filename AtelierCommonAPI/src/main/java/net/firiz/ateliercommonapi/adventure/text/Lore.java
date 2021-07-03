package net.firiz.ateliercommonapi.adventure.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class Lore extends ObjectArrayList<Component> {

    private static final String NULL_COMPONENT_ERR = "component is null.";
    private final boolean autoRemoveItalic;
    private transient ComponentWrapper<?> component;

    public Lore() {
        this(true);
    }

    public Lore(final Collection<Component> lore) {
        this(lore, true);
    }

    public Lore(@Nullable final String text) {
        this(text, true);
    }

    public Lore(final char value) {
        this(value, true);
    }

    public Lore(final byte value) {
        this(value, true);
    }

    public Lore(final short value) {
        this(value, true);
    }

    public Lore(final int value) {
        this(value, true);
    }

    public Lore(final long value) {
        this(value, true);
    }

    public Lore(final float value) {
        this(value, true);
    }

    public Lore(final double value) {
        this(value, true);
    }

    public Lore(final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
    }

    public Lore(final Collection<Component> lore, final boolean autoRemoveItalic) {
        this.autoRemoveItalic = autoRemoveItalic;
        addAll(lore);
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

    public static Lore asLore(Component... components) {
        final Lore lore = new Lore();
        lore.addAll(Arrays.asList(components));
        return lore;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public @NotNull Lore color(@Nullable final TextColor color) {
        Objects.requireNonNull(color, "color is null.");
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        this.component.color(color);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore deco(@NotNull final TextDecoration decoration) {
        Objects.requireNonNull(decoration, "decoration is null.");
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        this.component.decorate(decoration);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore deco(@NotNull final TextDecoration decoration, boolean available) {
        Objects.requireNonNull(decoration, "decoration is null.");
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        this.component.decoration(decoration, available);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore style(@NotNull final Style style) {
        this.component.style(style);
        return this;
    }

    public Lore nextLine() {
        return add("");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public boolean add(final Component component) {
        this.component = ComponentWrapper.wrap(component);
        if (autoRemoveItalic) {
            this.component.decoration(TextDecoration.ITALIC, false);
        }
        return super.add(this.component);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void add(final int i, final Component component) {
        this.component = ComponentWrapper.wrap(component);
        if (autoRemoveItalic) {
            this.component.decoration(TextDecoration.ITALIC, false);
        }
        super.add(i, this.component);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore add(@Nullable final String text) {
        this.component = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        if (autoRemoveItalic) {
            this.component.decoration(TextDecoration.ITALIC, false);
        }
        add(this.component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore add(final int i, @Nullable final String text) {
        this.component = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        if (autoRemoveItalic) {
            this.component.decoration(TextDecoration.ITALIC, false);
        }
        add(i, this.component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(@Nullable final String text) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(Objects.requireNonNullElse(text, "")));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final char value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final char value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, this.component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final Component component) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final ComponentWrapper<?> c = ComponentWrapper.wrap(component);
        this.component.append(c);
        this.component = c;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final char value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final byte value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final byte value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final byte value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final short value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final short value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final short value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final int value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final int value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final int value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final long value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final long value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final long value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final float value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final float value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final float value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    public Lore add(final double value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(this.component);
        return this;
    }

    public Lore add(final int i, final double value) {
        this.component = new TextComponentWrapper(Component.text(value));
        add(i, component);
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Lore append(final double value) {
        Objects.requireNonNull(this.component, NULL_COMPONENT_ERR);
        final TextComponentWrapper c = new TextComponentWrapper(Component.text(value));
        this.component.append(c);
        this.component = c;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Lore that = (Lore) o;
        return Objects.equals(this.component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), component);
    }

}
