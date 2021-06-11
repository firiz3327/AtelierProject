package net.firiz.ateliercommonapi.utils.pair.booleans;

import it.unimi.dsi.fastutil.objects.ObjectBooleanMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectBooleanNonNullMutablePair<K> extends ObjectBooleanMutablePair<K> {

    public ObjectBooleanNonNullMutablePair(@NotNull K left, boolean right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectBooleanMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
