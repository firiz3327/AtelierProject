package net.firiz.ateliercommonapi.utils.pair.longs;

import it.unimi.dsi.fastutil.objects.ObjectLongImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectLongNonNullImmutablePair<K> extends ObjectLongImmutablePair<K> {

    public ObjectLongNonNullImmutablePair(@NotNull K left, long right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
