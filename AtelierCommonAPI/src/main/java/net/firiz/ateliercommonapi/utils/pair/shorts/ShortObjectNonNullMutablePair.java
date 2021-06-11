package net.firiz.ateliercommonapi.utils.pair.shorts;

import it.unimi.dsi.fastutil.shorts.ShortObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ShortObjectNonNullMutablePair<V> extends ShortObjectMutablePair<V> {

    public ShortObjectNonNullMutablePair(short left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public ShortObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
