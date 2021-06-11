package net.firiz.ateliercommonapi.utils.pair.object;

import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectObjectNonNullImmutablePair<K, V> extends ObjectObjectImmutablePair<K, V> {

    public ObjectObjectNonNullImmutablePair(@NotNull K left, @NotNull V right) {
        super(Objects.requireNonNull(left), Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
