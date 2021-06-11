package net.firiz.ateliercommonapi.utils.pair.chars;

import it.unimi.dsi.fastutil.chars.CharObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CharObjectNonNullImmutablePair<V> extends CharObjectImmutablePair<V> {

    public CharObjectNonNullImmutablePair(char left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }
}
