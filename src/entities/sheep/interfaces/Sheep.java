package entities.sheep.interfaces;

import entities.interfaces.Identifiable;

public interface Sheep extends Identifiable {
    int getWool(int time);

    int getPeriod();
}
