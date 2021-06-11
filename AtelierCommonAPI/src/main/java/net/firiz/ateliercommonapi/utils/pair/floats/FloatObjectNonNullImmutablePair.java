package net.firiz.ateliercommonapi.utils.pair.floats;

import it.unimi.dsi.fastutil.floats.FloatObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FloatObjectNonNullImmutablePair<V> extends FloatObjectImmutablePair<V> {

    public FloatObjectNonNullImmutablePair(float left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
