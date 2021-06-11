package net.firiz.ateliercommonapi.utils.pair.booleans;

import it.unimi.dsi.fastutil.booleans.BooleanObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BooleanObjectNonNullImmutablePair<V> extends BooleanObjectImmutablePair<V> {

    public BooleanObjectNonNullImmutablePair(boolean left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
