package repositories;

import entities.sheep.interfaces.Sheep;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SheepRepository implements Repository<Sheep> {
    private List<Sheep> sheep;

    public SheepRepository() {
        sheep = new ArrayList<>();
    }

    @Override
    public void add(Sheep element) {
        sheep.add(element);
    }

    @Override
    public Sheep get(int index) {
        return sheep.get(index);
    }

    @Override
    public Sheep getById(String id) {
        return sheep.stream().filter((s) -> {
            return s.getId().equals(id);
        }).findFirst().orElse(null);
    }

    @Override
    public void sortBy(Comparator<Sheep> comparator) {
        sheep = sheep.stream().sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sheep> getEntities() {
        return this.sheep;
    }
}
