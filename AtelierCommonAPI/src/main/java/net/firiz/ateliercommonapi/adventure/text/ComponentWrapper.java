package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.*;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class ComponentWrapper<T extends ScopedComponent<T>> implements ScopedComponent<T> {

    T component;

    protected T set(T a) {
        this.component = a;
        return this.component;
    }

    public static ComponentWrapper<?> wrap(Component component) {
        if (component instanceof TextComponent) {
            return new TextComponentWrapper((TextComponent) component);
        } else if (component instanceof TranslatableComponent) {
            return new TranslatableComponentWrapper((TranslatableComponent) component);
        }
        throw new IllegalArgumentException("component isn't TextComponent class. " + component.getClass());
    }

    @Override
    public @NotNull T style(@NotNull Consumer<Style.Builder> style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull T style(Style.@NotNull Builder style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull T mergeStyle(@NotNull Component that) {
        return set(this.component.mergeStyle(that));
    }

    @Override
    public @NotNull T mergeStyle(@NotNull Component that, Style.@NotNull Merge @NotNull ... merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NotNull T append(@NotNull Component component) {
        return set(this.component.append(component));
    }

    @Override
    public @NotNull T append(@NotNull ComponentLike component) {
        return set(this.component.append(component));
    }

    @Override
    public @NotNull T append(@NotNull ComponentBuilder<?, ?> builder) {
        return set(this.component.append(builder));
    }

    @Override
    public @NotNull T mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NotNull T color(@Nullable TextColor color) {
        return set(this.component.color(color));
    }

    @Override
    public @NotNull T colorIfAbsent(@Nullable TextColor color) {
        return set(this.component.colorIfAbsent(color));
    }

    @Override
    public @NotNull Component decorate(@NotNull TextDecoration decoration) {
        return set(cast(this.component.decorate(decoration)));
    }

    @Override
    public @NotNull T decoration(@NotNull TextDecoration decoration, boolean flag) {
        return set(this.component.decoration(decoration, flag));
    }

    @Override
    public @NotNull T decoration(@NotNull TextDecoration decoration, TextDecoration.@NotNull State state) {
        return set(this.component.decoration(decoration, state));
    }

    @Override
    public @NotNull T clickEvent(@Nullable ClickEvent event) {
        return set(this.component.clickEvent(event));
    }

    @Override
    public @NotNull T hoverEvent(@Nullable HoverEventSource<?> event) {
        return set(this.component.hoverEvent(event));
    }

    @Override
    public @NotNull T insertion(@Nullable String insertion) {
        return set(this.component.insertion(insertion));
    }

    @Override
    public boolean contains(@NotNull Component that) {
        return this.component.contains(that);
    }

    @Override
    public @NotNull Component style(@NotNull Consumer<Style.Builder> consumer, Style.Merge.@NotNull Strategy strategy) {
        return set(cast(this.component.style(consumer, strategy)));
    }

    @Override
    public @Nullable TextColor color() {
        return this.component.color();
    }

    @Override
    public boolean hasDecoration(@NotNull TextDecoration decoration) {
        return this.component.hasDecoration(decoration);
    }

    @Override
    public TextDecoration.@NotNull State decoration(@NotNull TextDecoration decoration) {
        return this.component.decoration(decoration);
    }

    @Override
    public @NotNull Map<TextDecoration, TextDecoration.State> decorations() {
        return this.component.decorations();
    }

    @Override
    public @NotNull Component decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        return set(cast(this.component.decorations(decorations)));
    }

    @Override
    public @Nullable ClickEvent clickEvent() {
        return this.component.clickEvent();
    }

    @Override
    public @Nullable HoverEvent<?> hoverEvent() {
        return this.component.hoverEvent();
    }

    @Override
    public @Nullable String insertion() {
        return this.component.insertion();
    }

    @Override
    public boolean hasStyling() {
        return this.component.hasStyling();
    }

    @Override
    public @NotNull HoverEvent<Component> asHoverEvent() {
        return this.component.asHoverEvent();
    }

    @Override
    public @NotNull String examinableName() {
        return this.component.examinableName();
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return this.component.examinableProperties();
    }

    @Override
    public <R> @NotNull R examine(@NotNull Examiner<R> examiner) {
        return this.component.examine(examiner);
    }

    @Override
    public @NotNull @Unmodifiable List<Component> children() {
        return this.component.children();
    }

    @Override
    public @NotNull T children(@NotNull List<? extends ComponentLike> children) {
        return set(this.component.children(children));
    }

    @Override
    public @NotNull Style style() {
        return this.component.style();
    }

    @Override
    public @NotNull T style(@NotNull Style style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull Component replaceText(@NotNull Consumer<TextReplacementConfig.Builder> configurer) {
        return set(cast(this.component.replaceText(configurer)));
    }

    @Override
    public @NotNull Component replaceText(@NotNull TextReplacementConfig config) {
        return set(cast(this.component.replaceText(config)));
    }

    private T cast(Component component) {
        return (T) component;
    }

}
