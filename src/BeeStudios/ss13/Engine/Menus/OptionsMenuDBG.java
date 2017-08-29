package BeeStudios.ss13.Engine.Menus;

import BeeStudios.ss13.GAME_MAIN.Game_Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Spartan 2 on 2017-08-25.
 */
public class OptionsMenuDBG {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public OptionsMenuDBG(){
        prepareGUI();
    }
    public static void main(String[] args){
        OptionsMenuDBG launcherMenuDBG = new OptionsMenuDBG();
        launcherMenuDBG.showEventDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Launcher");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    public void showEventDemo(){
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("Launch");
        JButton OptionsButton = new JButton("Options");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("LaunchGame");
        OptionsButton.setActionCommand("OptionsMenu");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        OptionsButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
        controlPanel.add(OptionsButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "LaunchGame" ))  {
                statusLabel.setText("Ok Button clicked.");
                mainFrame.dispose();
                Game_Main gamex = new Game_Main();
                gamex.run(3);
                //mainFrame.setVisible(false);
            } else if( command.equals( "OptionsMenu" ) )  {
                statusLabel.setText("Submit Button clicked.");
            } else if( command.equals( "Cancel" )) {
                statusLabel.setText("Cancel Button clicked.");
                mainFrame.dispose();
            }
        }
    }
}
