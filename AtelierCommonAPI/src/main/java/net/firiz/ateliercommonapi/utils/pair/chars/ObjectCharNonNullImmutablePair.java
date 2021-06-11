package net.firiz.ateliercommonapi.utils.pair.chars;

import it.unimi.dsi.fastutil.objects.ObjectCharImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectCharNonNullImmutablePair<K> extends ObjectCharImmutablePair<K> {

    public ObjectCharNonNullImmutablePair(@NotNull K left, char right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

}
