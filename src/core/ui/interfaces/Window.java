package core.ui.interfaces;

public interface Window {
    void show(String name, int sizeX, int sizeY, boolean canResize,
              boolean exitOperationStopProcess);

    void handle(int id);
}
