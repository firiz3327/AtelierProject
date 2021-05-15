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
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
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
    public @NonNull TextComponent style(@NonNull Consumer<Style.Builder> style) {
        return set(this.component.style(style));
    }

    @Override
    public @NonNull TextComponent style(Style.@NonNull Builder style) {
        return set(this.component.style(style));
    }

    @Override
    public @NonNull TextComponent mergeStyle(@NonNull Component that) {
        return set(this.component.mergeStyle(that));
    }

    @Override
    public @NonNull TextComponent mergeStyle(@NonNull Component that, Style.@NonNull Merge @NonNull ... merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NonNull TextComponent append(@NonNull Component component) {
        return set(this.component.append(component));
    }

    @Override
    public @NonNull TextComponent append(@NonNull ComponentLike component) {
        return set(this.component.append(component));
    }

    @Override
    public @NonNull TextComponent append(@NonNull ComponentBuilder<?, ?> builder) {
        return set(this.component.append(builder));
    }

    @Override
    public @NonNull TextComponent mergeStyle(@NonNull Component that, @NonNull Set<Style.Merge> merges) {
        return set(this.component.mergeStyle(that, merges));
    }

    @Override
    public @NonNull TextComponent color(@Nullable TextColor color) {
        return set(this.component.color(color));
    }

    @Override
    public @NonNull TextComponent colorIfAbsent(@Nullable TextColor color) {
        return set(this.component.colorIfAbsent(color));
    }

    @Override
    public @NonNull Component decorate(@NonNull TextDecoration decoration) {
        return set((TextComponent) this.component.decorate(decoration));
    }

    @Override
    public @NonNull TextComponent decoration(@NonNull TextDecoration decoration, boolean flag) {
        return set(this.component.decoration(decoration, flag));
    }

    @Override
    public @NonNull TextComponent decoration(@NonNull TextDecoration decoration, TextDecoration.@NonNull State state) {
        return set(this.component.decoration(decoration, state));
    }

    @Override
    public @NonNull TextComponent clickEvent(@Nullable ClickEvent event) {
        return set(this.component.clickEvent(event));
    }

    @Override
    public @NonNull TextComponent hoverEvent(@Nullable HoverEventSource<?> event) {
        return set(this.component.hoverEvent(event));
    }

    @Override
    public @NonNull TextComponent insertion(@Nullable String insertion) {
        return set(this.component.insertion(insertion));
    }

    @Override
    public boolean contains(@NonNull Component that) {
        return this.component.contains(that);
    }

    @Override
    public @NonNull Component style(@NonNull Consumer<Style.Builder> consumer, Style.Merge.@NonNull Strategy strategy) {
        return set((TextComponent) this.component.style(consumer, strategy));
    }

    @Override
    public @Nullable TextColor color() {
        return this.component.color();
    }

    @Override
    public boolean hasDecoration(@NonNull TextDecoration decoration) {
        return this.component.hasDecoration(decoration);
    }

    @Override
    public TextDecoration.@NonNull State decoration(@NonNull TextDecoration decoration) {
        return this.component.decoration(decoration);
    }

    @Override
    public @NonNull Map<TextDecoration, TextDecoration.State> decorations() {
        return this.component.decorations();
    }

    @Override
    public @NonNull Component decorations(@NonNull Map<TextDecoration, TextDecoration.State> decorations) {
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
    public @NonNull HoverEvent<Component> asHoverEvent() {
        return this.component.asHoverEvent();
    }

    @Override
    public @NonNull String examinableName() {
        return this.component.examinableName();
    }

    @Override
    public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
        return this.component.examinableProperties();
    }

    @Override
    public <R> @NonNull R examine(@NonNull Examiner<R> examiner) {
        return this.component.examine(examiner);
    }

    @Override
    public @NonNull String content() {
        return this.component.content();
    }

    @Override
    public @NonNull TextComponent content(@NonNull String content) {
        return set(this.component.content(content));
    }

    @Override
    public @NonNull Builder toBuilder() {
        return this.component.toBuilder();
    }

    @Override
    public @NonNull @Unmodifiable List<Component> children() {
        return this.component.children();
    }

    @Override
    public @NonNull TextComponent children(@NonNull List<? extends ComponentLike> children) {
        return set(this.component.children(children));
    }

    @Override
    public @NonNull Style style() {
        return this.component.style();
    }

    @Override
    public @NonNull TextComponent style(@NonNull Style style) {
        return set(this.component.style(style));
    }

    @Override
    public @NonNull Component replaceText(@NonNull Consumer<TextReplacementConfig.Builder> configurer) {
        return set((TextComponent) this.component.replaceText(configurer));
    }

    @Override
    public @NonNull Component replaceText(@NonNull TextReplacementConfig config) {
        return set((TextComponent) this.component.replaceText(config));
    }
}
