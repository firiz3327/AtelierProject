package net.firiz.ateliercommonapi.utils.pair.doubles;

import it.unimi.dsi.fastutil.objects.ObjectDoubleMutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectDoubleNonNullMutablePair<K> extends ObjectDoubleMutablePair<K> {

    public ObjectDoubleNonNullMutablePair(@NotNull K left, double right) {
        super(Objects.requireNonNull(left), right);
    }

    @NotNull
    @Override
    public K left() {
        return super.left();
    }

    @Override
    public ObjectDoubleMutablePair<K> left(@NotNull K l) {
        return super.left(Objects.requireNonNull(l));
    }
}
