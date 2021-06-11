package net.firiz.ateliercommonapi.utils.pair.floats;

import it.unimi.dsi.fastutil.objects.ObjectFloatMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectFloatNonNullMutablePair<K> extends ObjectFloatMutablePair<K> {

    public ObjectFloatNonNullMutablePair(@NotNull K left, float right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectFloatMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
