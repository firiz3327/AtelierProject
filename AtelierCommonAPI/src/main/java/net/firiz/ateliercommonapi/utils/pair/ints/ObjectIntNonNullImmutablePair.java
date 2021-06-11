package net.firiz.ateliercommonapi.utils.pair.ints;

import it.unimi.dsi.fastutil.objects.ObjectIntImmutablePair;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectIntNonNullImmutablePair<K> extends ObjectIntImmutablePair<K> {

    public ObjectIntNonNullImmutablePair(@NotNull K left, int right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
