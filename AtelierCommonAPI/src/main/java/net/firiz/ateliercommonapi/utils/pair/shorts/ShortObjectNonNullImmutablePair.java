package net.firiz.ateliercommonapi.utils.pair.shorts;

import it.unimi.dsi.fastutil.shorts.ShortObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ShortObjectNonNullImmutablePair<V> extends ShortObjectImmutablePair<V> {

    public ShortObjectNonNullImmutablePair(short left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
