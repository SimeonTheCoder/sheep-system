import core.ui.WindowImpl;
import core.ui.interfaces.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new WindowImpl();

        window.show("Sheep Organiser v0.1", 500, 500,
                false, true);
    }
}
