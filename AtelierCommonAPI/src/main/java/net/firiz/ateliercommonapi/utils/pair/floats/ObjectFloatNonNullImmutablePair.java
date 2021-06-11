package net.firiz.ateliercommonapi.utils.pair.floats;

import it.unimi.dsi.fastutil.objects.ObjectFloatImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectFloatNonNullImmutablePair<K> extends ObjectFloatImmutablePair<K> {

    public ObjectFloatNonNullImmutablePair(@NotNull K left, float right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
