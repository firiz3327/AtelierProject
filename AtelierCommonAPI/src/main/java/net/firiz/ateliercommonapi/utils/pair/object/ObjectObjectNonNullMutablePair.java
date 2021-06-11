package net.firiz.ateliercommonapi.utils.pair.object;

import it.unimi.dsi.fastutil.objects.ObjectObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectObjectNonNullMutablePair<K, V> extends ObjectObjectMutablePair<K, V> {

    public ObjectObjectNonNullMutablePair(@NotNull K left, @NotNull V right) {
        super(Objects.requireNonNull(left), Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectObjectMutablePair<K, V> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public ObjectObjectMutablePair<K, V> right(V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
