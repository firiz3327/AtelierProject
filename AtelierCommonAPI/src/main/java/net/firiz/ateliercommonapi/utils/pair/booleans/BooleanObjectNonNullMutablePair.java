package net.firiz.ateliercommonapi.utils.pair.booleans;

import it.unimi.dsi.fastutil.booleans.BooleanObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BooleanObjectNonNullMutablePair<V> extends BooleanObjectMutablePair<V> {

    public BooleanObjectNonNullMutablePair(boolean left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public BooleanObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
