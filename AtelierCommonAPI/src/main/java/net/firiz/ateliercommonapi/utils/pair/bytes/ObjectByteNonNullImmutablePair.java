package net.firiz.ateliercommonapi.utils.pair.bytes;

import it.unimi.dsi.fastutil.objects.ObjectByteImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectByteNonNullImmutablePair<K> extends ObjectByteImmutablePair<K> {

    public ObjectByteNonNullImmutablePair(@NotNull K left, byte right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
