package net.firiz.ateliercommonapi.utils.pair.bytes;

import it.unimi.dsi.fastutil.bytes.ByteObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ByteObjectNonNullMutablePair<V> extends ByteObjectMutablePair<V> {

    public ByteObjectNonNullMutablePair(byte left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public ByteObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
