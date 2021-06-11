package net.firiz.ateliercommonapi.utils.pair.ints;

import it.unimi.dsi.fastutil.objects.ObjectIntMutablePair;
import it.unimi.dsi.fastutil.objects.ObjectObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectIntNonNullMutablePair<K> extends ObjectIntMutablePair<K> {

    public ObjectIntNonNullMutablePair(@NotNull K left, int right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectIntMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
