package dk.sdu.mmmi.cbse.common.util;

public interface AbstractFactory<T> {
    T create(String entityType);
}
