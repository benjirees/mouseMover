import java.awt.event.InputEvent;
import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * @author Ben Rees
 * @version 1.0
 *
 * Moves mouse to random coordinates (SCREEN_WIDTH/2, SCREEN_HEIGHT/2)
 * Opens notepad.exe
 * Generates random coordinates every loop iteration and then moves mouse to coordinates
 */
public class MouseMover {

    private int totalDistance;
    private boolean run;
    private int randX;
    private int randY;
    private int count;
    private boolean hasMouseMoved;
    private Random rn = new Random();
    private Robot robot = new Robot();
    private Runtime rs = Runtime.getRuntime();

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SCREEN_WIDTH = (int)size.getWidth();
    private final int SCREEN_HEIGHT = (int)size.getHeight();

    public MouseMover() throws AWTException {
    }

    /**
     *
     * @throws InterruptedException exception
     * @throws IOException exception
     */
    public void run() throws InterruptedException, IOException {

        Thread.sleep(3000);
        totalDistance = 0;
        run = true;
        randX = genRandX();
        randY = genRandY();
        totalDistance += (randX + randY);
        moveTheMouse(randX, randY);
        count = 0;
        openNotepad();
        while (run) {
            Thread.sleep(500);
            count++;
            hasMouseMoved = mouseMoved(randX, randY);
            if (hasMouseMoved) {
                if (count % 5 == 0) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                }
                randY = genRandY();
                moveTheMouse(randX, randY);
                randX = genRandX();
            } else {
                rs.exec("taskkill /F /PID notepad.exe");
                run = false;
            }

        }

    }

    private void moveTheMouse(int x, int y) {
        robot.mouseMove(x, y);
    }

    private int genRandX() {
        return rn.nextInt((SCREEN_WIDTH/2+40 - SCREEN_WIDTH/2) + 1) + SCREEN_WIDTH/2;
    }

    private int genRandY() {
        return rn.nextInt((SCREEN_HEIGHT/2+40 - SCREEN_HEIGHT/2) + 1) + SCREEN_HEIGHT/2;
    }

    private void openNotepad() {
        try {
            rs.exec("cmd /c START /MAX notepad.exe"); // Opens notepad in fullscreen
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private boolean mouseMoved(int currentX, int currentY) {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int supposedCurrentX = (int) b.getX();
        int supposedCurrentY = (int) b.getY();

        int xDifference = currentX - supposedCurrentX;
        int yDifference = currentY - supposedCurrentY;

        // mouse has moved so stop if false:
        return xDifference <= 100 && xDifference >= -100 && yDifference <= 100 && yDifference >= -100;

    }

}
