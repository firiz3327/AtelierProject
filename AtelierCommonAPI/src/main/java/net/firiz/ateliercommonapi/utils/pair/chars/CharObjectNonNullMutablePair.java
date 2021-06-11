package net.firiz.ateliercommonapi.utils.pair.chars;

import it.unimi.dsi.fastutil.chars.CharObjectMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CharObjectNonNullMutablePair<V> extends CharObjectMutablePair<V> {

    public CharObjectNonNullMutablePair(char left, @NotNull V right) {
        super(left, Objects.requireNonNull(right));
    }

    @NotNull
    @Override
    public V right() {
        return super.right();
    }

    @Override
    public CharObjectMutablePair<V> right(@NotNull V r) {
        return super.right(Objects.requireNonNull(r));
    }
}
