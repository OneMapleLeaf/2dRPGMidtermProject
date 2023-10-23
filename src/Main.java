import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SoulKnight: Java Edition");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/SJE_Icon.png")));

        panel.startGameThread();
    }
}