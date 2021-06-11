package net.firiz.ateliercommonapi.utils.pair.chars;

import it.unimi.dsi.fastutil.objects.ObjectCharMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectCharNonNullMutablePair<K> extends ObjectCharMutablePair<K> {

    public ObjectCharNonNullMutablePair(@NotNull K left, char right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectCharMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
