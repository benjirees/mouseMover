import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Ben Rees
 * @version 1.0
 *
 * Template class structures the main program:
 *  <ol>
 *      <li>Uses JFrame to create interactive GUI</li>
 *      <li>Button processing occurs here</li>
 *      <li>extends JFrame</li>
 *      <li>implements ActionListener</li>
 *  </ol>
 */

public class Template extends JFrame implements ActionListener {

    private JButton mouseMover;
    private JButton screenLock;
    private JButton exit;
    private MouseMover moveMouse = new MouseMover();
    private ScreenLock lockScreen = new ScreenLock();

    /**
     * Template constructor - Creates GUI when called
     * <ul>
     *     <li>Creates frame of program</li>
     *     <li>Generates and places buttons</li>
     * </ul>
     *
     * @throws AWTException Throws AWTException
     */
    public Template() throws AWTException {
        JFrame f = new JFrame("Office Tools"); // Creates new instance of JFrame

        // Button for 'Mouse Mover':
        mouseMover = new JButton("Mouse Mover");
        mouseMover.setBounds(105, 50, 150, 40);
        mouseMover.setBackground(Color.lightGray);
        mouseMover.addActionListener(this);

        // Add 'Mouse Mover' button to screen:
        f.add(mouseMover);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);

        screenLock = new JButton("Screen Lock");
        screenLock.setBounds(105, 150, 150, 40);
        screenLock.setBackground(Color.lightGray);
        screenLock.addActionListener(this);

        f.add(screenLock);

        // Exit button:
        exit = new JButton("Exit");
        exit.setBounds(105, 250, 150, 40);
        exit.setBackground(Color.lightGray);
        exit.addActionListener(this);

        // Add 'Exit' button to screen:
        f.add(exit);

    }

    /**
     * Reacts appropriately when buttons are clicked
     * <ul>
     *     <li>Mouse Mover - creates new instance of 'MouseMover'</li>
     *     <li>Screen Lock - creates new instance of 'ScreenLock'</li>
     *     <li>Exit - Exits program gracefully</li>
     * </ul>
     *
     * @param e mouse click (Event)
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Mouse Mover")) {

            try {
                moveMouse.run();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(1);
        } else if (e.getActionCommand().equals("Screen Lock")) {
            System.out.println("WORKS");
            lockScreen.run();
        }
    }
}
