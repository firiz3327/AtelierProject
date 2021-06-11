package net.firiz.ateliercommonapi.utils.pair.doubles;

import it.unimi.dsi.fastutil.objects.ObjectDoubleImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectDoubleNonNullImmutablePair<K> extends ObjectDoubleImmutablePair<K> {

    public ObjectDoubleNonNullImmutablePair(@NotNull K left, double right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
