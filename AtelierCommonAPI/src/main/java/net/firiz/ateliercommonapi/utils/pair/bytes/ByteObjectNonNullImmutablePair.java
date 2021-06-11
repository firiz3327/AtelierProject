package net.firiz.ateliercommonapi.utils.pair.bytes;

import it.unimi.dsi.fastutil.bytes.ByteObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ByteObjectNonNullImmutablePair<V> extends ByteObjectImmutablePair<V> {

    public ByteObjectNonNullImmutablePair(byte left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
