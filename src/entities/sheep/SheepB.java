package entities.sheep;

public class SheepB extends BaseSheep{
    private static final int TIME_PER_WOOL = 65;
    private static final int WOOL_QUANTITY_MIN = 4;
    private static final int WOOL_QUANTITY_MAX = 10;

    public SheepB(String id) {
        super(TIME_PER_WOOL, WOOL_QUANTITY_MIN, WOOL_QUANTITY_MAX, id);
    }
}
