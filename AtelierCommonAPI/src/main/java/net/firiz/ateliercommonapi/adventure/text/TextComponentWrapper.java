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

class TextComponentWrapper implements TextComponent {

    TextComponent component;

    TextComponentWrapper(TextComponent component) {
        this.component = component;
    }

    private TextComponent set(TextComponent a) {
        this.component = a;
        return this.component;
    }

    @Override
    public @NotNull TextComponent style(@NotNull Consumer<Style.Builder> style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull TextComponent style(Style.@NotNull Builder style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull TextComponent mergeStyle(@NotNull Component that) {
        return set(this.component.mergeStyle(that));
    }

    @Override
    public @NotNull TextComponent mergeStyle(@NotNull Component that, Style.@NotNull Merge @NotNull ... merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NotNull TextComponent append(@NotNull Component component) {
        return set(this.component.append(component));
    }

    @Override
    public @NotNull TextComponent append(@NotNull ComponentLike component) {
        return set(this.component.append(component));
    }

    @Override
    public @NotNull TextComponent append(@NotNull ComponentBuilder<?, ?> builder) {
        return set(this.component.append(builder));
    }

    @Override
    public @NotNull TextComponent mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NotNull TextComponent color(@Nullable TextColor color) {
        return set(this.component.color(color));
    }

    @Override
    public @NotNull TextComponent colorIfAbsent(@Nullable TextColor color) {
        return set(this.component.colorIfAbsent(color));
    }

    @Override
    public @NotNull Component decorate(@NotNull TextDecoration decoration) {
        return set((TextComponent) this.component.decorate(decoration));
    }

    @Override
    public @NotNull TextComponent decoration(@NotNull TextDecoration decoration, boolean flag) {
        return set(this.component.decoration(decoration, flag));
    }

    @Override
    public @NotNull TextComponent decoration(@NotNull TextDecoration decoration, TextDecoration.@NotNull State state) {
        return set(this.component.decoration(decoration, state));
    }

    @Override
    public @NotNull TextComponent clickEvent(@Nullable ClickEvent event) {
        return set(this.component.clickEvent(event));
    }

    @Override
    public @NotNull TextComponent hoverEvent(@Nullable HoverEventSource<?> event) {
        return set(this.component.hoverEvent(event));
    }

    @Override
    public @NotNull TextComponent insertion(@Nullable String insertion) {
        return set(this.component.insertion(insertion));
    }

    @Override
    public boolean contains(@NotNull Component that) {
        return this.component.contains(that);
    }

    @Override
    public @NotNull Component style(@NotNull Consumer<Style.Builder> consumer, Style.Merge.@NotNull Strategy strategy) {
        return set((TextComponent) this.component.style(consumer, strategy));
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
        return set((TextComponent) this.component.decorations(decorations));
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
    public @NotNull String content() {
        return this.component.content();
    }

    @Override
    public @NotNull TextComponent content(@NotNull String content) {
        return set(this.component.content(content));
    }

    @Override
    public @NotNull Builder toBuilder() {
        return this.component.toBuilder();
    }

    @Override
    public @NotNull @Unmodifiable List<Component> children() {
        return this.component.children();
    }

    @Override
    public @NotNull TextComponent children(@NotNull List<? extends ComponentLike> children) {
        return set(this.component.children(children));
    }

    @Override
    public @NotNull Style style() {
        return this.component.style();
    }

    @Override
    public @NotNull TextComponent style(@NotNull Style style) {
        return set(this.component.style(style));
    }

    @Override
    public @NotNull Component replaceText(@NotNull Consumer<TextReplacementConfig.Builder> configurer) {
        return set((TextComponent) this.component.replaceText(configurer));
    }

    @Override
    public @NotNull Component replaceText(@NotNull TextReplacementConfig config) {
        return set((TextComponent) this.component.replaceText(config));
    }
}
