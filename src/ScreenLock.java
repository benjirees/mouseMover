import java.awt.*;

public class ScreenLock {

    private Robot robot = new Robot();
    private boolean run;
    private int currentX;

    // Get monitor height:
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SCREEN_HEIGHT = (int)size.getHeight();

    // Cursor info:
    private PointerInfo a = MouseInfo.getPointerInfo();
    private Point b = a.getLocation();

    public ScreenLock() throws AWTException {
    }

    public void run() {
        run = true;
        while (run) {
            currentX = getCurrentX();
            System.out.println(currentX);
            // if currentX between 0 (bottom left) and monitor height (top left)
            if (currentX == 0) {
                // Move mouse back inwards:
                robot.mouseMove(currentX+5, (int)b.getY());
            }
        }
    }

    private int getCurrentX() {
        return (int) b.getX();
    }

}
