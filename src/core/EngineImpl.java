package core;

import core.factories.SheepFactory;
import core.factories.interfaces.Factory;
import core.interfaces.Engine;
import entities.sheep.interfaces.Sheep;
import repositories.SheepRepository;

import java.util.Arrays;

public class EngineImpl implements Engine {
    private SheepRepository sheepRepository;

    private int woolCollected;

    private int time;

    public EngineImpl(SheepRepository sheepRepository) {
        this.sheepRepository = sheepRepository;

        woolCollected = 0;
    }

    public EngineImpl() {
        sheepRepository = new SheepRepository();

        woolCollected = 0;
    }

    @Override
    public void interpret(String[] data) {
        switch (data[0]) {
            case "add":
                Factory<Sheep> factory = new SheepFactory();

                sheepRepository.add(factory.create(Arrays.stream(data)
                        .skip(1).toArray(String[]::new)));

                break;

            case "sheer":
                String id = data[1];

                int wool = sheepRepository.getById(id).getWool(time);

                woolCollected += wool;

                break;
        }
    }

    public int getWoolCollected() {
        return this.woolCollected;
    }

    public int getTime() {
        return this.time;
    }

    public void tick() {
        time ++;
    }
}
