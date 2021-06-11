package net.firiz.ateliercommonapi.utils.pair.shorts;

import it.unimi.dsi.fastutil.objects.ObjectShortImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectShortNonNullImmutablePair<K> extends ObjectShortImmutablePair<K> {

    public ObjectShortNonNullImmutablePair(@NotNull K left, short right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
