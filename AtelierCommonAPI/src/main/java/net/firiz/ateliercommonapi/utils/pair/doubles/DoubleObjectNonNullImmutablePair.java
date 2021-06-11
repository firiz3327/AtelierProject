package net.firiz.ateliercommonapi.utils.pair.doubles;

import it.unimi.dsi.fastutil.doubles.DoubleObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DoubleObjectNonNullImmutablePair<V> extends DoubleObjectImmutablePair<V> {

    public DoubleObjectNonNullImmutablePair(double left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
