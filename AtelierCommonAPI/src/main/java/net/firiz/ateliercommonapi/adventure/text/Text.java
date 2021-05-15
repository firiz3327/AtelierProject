package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Text extends TextComponentWrapper {

    private TextComponentWrapper lastComponent;

    public Text() {
        this(false);
    }

    public Text(@Nullable final String text) {
        this(text, false);
    }

    public Text(final char value) {
        this(value, false);
    }

    public Text(final byte value) {
        this(value, false);
    }

    public Text(final short value) {
        this(value, false);
    }

    public Text(final int value) {
        this(value, false);
    }

    public Text(final long value) {
        this(value, false);
    }

    public Text(final float value) {
        this(value, false);
    }

    public Text(final double value) {
        this(value, false);
    }

    public Text(final boolean autoRemoveItalic) {
        super(Component.text(""));
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

    public Text color(@NotNull final C color) {
        return color(color.getColor());
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
