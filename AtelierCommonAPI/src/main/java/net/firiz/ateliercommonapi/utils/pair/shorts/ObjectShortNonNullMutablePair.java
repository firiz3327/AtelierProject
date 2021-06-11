package net.firiz.ateliercommonapi.utils.pair.shorts;

import it.unimi.dsi.fastutil.objects.ObjectShortMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectShortNonNullMutablePair<K> extends ObjectShortMutablePair<K> {

    public ObjectShortNonNullMutablePair(@NotNull K left, short right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectShortMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
