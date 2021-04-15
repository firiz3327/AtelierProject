package net.firiz.atelierconstruction;

import java.lang.reflect.Field;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static <T> ReflectionComponent<T> set(T target, Class<?> clazz, String name, Object value) {
        return new ReflectionComponent<>(target, clazz).set(name, value);
    }

    public static class ReflectionComponent<T> {
        private final T target;
        private Class<?> clazz;

        public ReflectionComponent(T target, Class<?> clazz) {
            this.target = target;
            this.clazz = clazz;
        }

        public ReflectionComponent<T> set(Class<?> clazz, String name, Object value) {
            this.clazz = clazz;
            return set(name, value);
        }

        public ReflectionComponent<T> set(String name, Object value) {
            try {
                final Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(target, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("not found field " + name + ".");
            }
            return this;
        }

        public T get() {
            return target;
        }

    }

}
