package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

class TextComponentWrapper extends ComponentWrapper<TextComponent> implements TextComponent {

    TextComponentWrapper(TextComponent component) {
        this.component = component;
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
    public @NotNull TextComponent.Builder toBuilder() {
        return this.component.toBuilder();
    }
}
