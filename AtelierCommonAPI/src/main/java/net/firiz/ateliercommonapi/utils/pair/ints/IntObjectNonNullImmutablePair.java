package net.firiz.ateliercommonapi.utils.pair.ints;

import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IntObjectNonNullImmutablePair<V> extends IntObjectImmutablePair<V> {

    public IntObjectNonNullImmutablePair(int left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
