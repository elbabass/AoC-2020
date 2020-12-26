package org.util.AoC2020.D04;

import java.util.Objects;

public abstract class Identifier<T> {
    protected final T value;

    public Identifier(T identifier) {
        this.value = identifier;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;
        Identifier<?> that = (Identifier<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
