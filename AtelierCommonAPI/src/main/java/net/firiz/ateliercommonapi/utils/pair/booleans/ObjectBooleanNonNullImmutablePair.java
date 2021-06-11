package net.firiz.ateliercommonapi.utils.pair.booleans;

import it.unimi.dsi.fastutil.objects.ObjectBooleanImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectBooleanNonNullImmutablePair<K> extends ObjectBooleanImmutablePair<K> {

    public ObjectBooleanNonNullImmutablePair(@NotNull K left, boolean right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
