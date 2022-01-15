package entities.sheep;

import entities.sheep.interfaces.Sheep;

import java.util.Random;

public abstract class BaseSheep implements Sheep {
    private String id;

    private int timePerWool;
    private int woolQuantityMin;
    private int woolQuantityMax;

    private Random random;

    public BaseSheep(int timePerWool, int woolQuantityMin, int woolQuantityMax, String id) {
        this.timePerWool = timePerWool;

        this.woolQuantityMin = woolQuantityMin;
        this.woolQuantityMax = woolQuantityMax;

        this.id = id;

        random = new Random();
    }

    @Override
    public int getWool(int time) {
        if(time % timePerWool == 0) {
            return (random.nextInt(woolQuantityMax) % (woolQuantityMax - woolQuantityMin)) + woolQuantityMin;
        }

        return 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getPeriod() {
        return timePerWool;
    }
}
