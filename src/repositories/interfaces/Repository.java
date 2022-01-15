package repositories.interfaces;

import java.util.Comparator;
import java.util.List;

public interface Repository<T> {
    void add(T element);

    T get(int index);

    T getById(String id);

    void sortBy(Comparator<T> comparator);

    List<T> getEntities();
}
