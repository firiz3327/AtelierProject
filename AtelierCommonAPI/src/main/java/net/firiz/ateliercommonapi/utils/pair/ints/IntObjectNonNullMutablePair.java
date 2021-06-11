package net.firiz.ateliercommonapi.utils.pair.ints;

import it.unimi.dsi.fastutil.ints.IntObjectMutablePair;
import it.unimi.dsi.fastutil.objects.ObjectObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IntObjectNonNullMutablePair<V> extends IntObjectMutablePair<V> {

    public IntObjectNonNullMutablePair(int left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public IntObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
