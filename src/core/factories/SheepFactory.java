package core.factories;

import core.factories.interfaces.Factory;
import entities.sheep.SheepA;
import entities.sheep.SheepB;
import entities.sheep.SheepC;
import entities.sheep.interfaces.Sheep;

public class SheepFactory implements Factory<Sheep> {

    @Override
    public Sheep create(String[] data) {
        String id = data[1];

        Sheep sheep = null;

        switch (data[0]) {
            case "SheepA":
                sheep = new SheepA(id);

                break;

            case "SheepB":
                sheep = new SheepB(id);

                break;

            case "SheepC":
                sheep = new SheepC(id);

                break;

            default:
                throw new IllegalArgumentException("Invalid Sheep Type!");
        }

        return sheep;
    }
}
