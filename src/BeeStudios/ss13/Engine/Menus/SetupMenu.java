package BeeStudios.ss13.Engine.Menus;

import javax.swing.*;

/**
 * Created by Spartan 2 on 2017-08-25.
 */
public class SetupMenu {
    private JButton LaunchButton;
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SetupMenu");
        frame.setContentPane(new SetupMenu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
