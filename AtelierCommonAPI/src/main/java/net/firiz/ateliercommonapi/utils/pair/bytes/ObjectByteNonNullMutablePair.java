package net.firiz.ateliercommonapi.utils.pair.bytes;

import it.unimi.dsi.fastutil.objects.ObjectByteMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectByteNonNullMutablePair<K> extends ObjectByteMutablePair<K> {

    public ObjectByteNonNullMutablePair(@NotNull K left, byte right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectByteMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
