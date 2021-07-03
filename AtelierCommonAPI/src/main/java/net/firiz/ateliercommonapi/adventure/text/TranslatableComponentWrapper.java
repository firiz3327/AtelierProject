package net.firiz.ateliercommonapi.adventure.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class TranslatableComponentWrapper extends ComponentWrapper<TranslatableComponent> implements TranslatableComponent {

    TranslatableComponentWrapper(TranslatableComponent component) {
        this.component = component;
    }

    @Override
    public @NotNull String key() {
        return this.component.key();
    }

    @Override
    public @NotNull TranslatableComponent key(@NotNull String key) {
        return this.component.key(key);
    }

    @Override
    public @NotNull List<Component> args() {
        return this.component.args();
    }

    @Override
    public @NotNull TranslatableComponent args(@NotNull ComponentLike @NotNull ... args) {
        return this.component.args(args);
    }

    @Override
    public @NotNull TranslatableComponent args(@NotNull List<? extends ComponentLike> args) {
        return this.component.args(args);
    }

    @Override
    public @NotNull Builder toBuilder() {
        return this.component.toBuilder();
    }
}
