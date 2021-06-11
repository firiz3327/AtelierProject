package net.firiz.ateliercommonapi.utils.pair.floats;

import it.unimi.dsi.fastutil.floats.FloatObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FloatObjectNonNullMutablePair<V> extends FloatObjectMutablePair<V> {

    public FloatObjectNonNullMutablePair(float left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public FloatObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
