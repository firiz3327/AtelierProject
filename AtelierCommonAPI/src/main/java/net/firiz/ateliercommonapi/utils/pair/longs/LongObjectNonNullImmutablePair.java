package net.firiz.ateliercommonapi.utils.pair.longs;

import it.unimi.dsi.fastutil.longs.LongObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LongObjectNonNullImmutablePair<V> extends LongObjectImmutablePair<V> {

    public LongObjectNonNullImmutablePair(long left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
