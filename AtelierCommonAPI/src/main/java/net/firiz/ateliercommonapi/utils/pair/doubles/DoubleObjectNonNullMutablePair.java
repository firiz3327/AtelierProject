package net.firiz.ateliercommonapi.utils.pair.doubles;

import it.unimi.dsi.fastutil.doubles.DoubleObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DoubleObjectNonNullMutablePair<V> extends DoubleObjectMutablePair<V> {

    public DoubleObjectNonNullMutablePair(double left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public DoubleObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
