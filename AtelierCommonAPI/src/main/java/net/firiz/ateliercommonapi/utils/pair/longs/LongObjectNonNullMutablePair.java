package net.firiz.ateliercommonapi.utils.pair.longs;

import it.unimi.dsi.fastutil.longs.LongObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LongObjectNonNullMutablePair<V> extends LongObjectMutablePair<V> {

    public LongObjectNonNullMutablePair(long left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public LongObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
