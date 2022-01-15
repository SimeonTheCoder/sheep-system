package core.factories.interfaces;

public interface Factory<T> {
    T create(String[] data);
}
