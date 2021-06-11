package net.firiz.ateliercommonapi.utils.pair.longs;

import it.unimi.dsi.fastutil.objects.ObjectLongMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectLongNonNullMutablePair<K> extends ObjectLongMutablePair<K> {

    public ObjectLongNonNullMutablePair(@NotNull K left, long right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectLongMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
