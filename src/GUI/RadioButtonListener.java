package GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.JFrame;

public class RadioButtonListener implements ActionListener{

    //Insatance Variables for RadioButtonListener
    private GraphGUI gui;

    /*
     * One Parameter Constructor of a RadioButtonListener
     * @param gui the current frame that has the canvas
     */
    public RadioButtonListener(GraphGUI gui) {
        this.gui=gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * If any of the radio buttons are chosen, all
         * radio buttons other are disabled until the user
         * exits out of the current option
         */



        if(gui.buttons[0].isSelected()) {
            disableUnselectedButtons(0);
            gui.canvas.setRadioButtonState("add node");
            gui.canvas.setIsEnabled(true);


        }else if(gui.buttons[1].isSelected()) {
            disableUnselectedButtons(1);
            gui.canvas.setRadioButtonState("remove node");
            gui.canvas.setIsEnabled(true);


        }else if(gui.buttons[2].isSelected()) {
            disableUnselectedButtons(2);
            gui.canvas.setRadioButtonState("connect edge");
            gui.canvas.setIsEnabled(true);

        }else if(gui.buttons[3].isSelected()) {
            disableUnselectedButtons(3);
            gui.canvas.setRadioButtonState("remove edge");
            gui.canvas.setIsEnabled(true);



        }else if(gui.buttons[4].isSelected()) {
            disableUnselectedButtons(4);
            gui.canvas.setRadioButtonState("shortest path");
            gui.canvas.setIsEnabled(true);

        }else if(gui.buttons[5].isSelected()) {
            disableUnselectedButtons(5);
            gui.canvas.setRadioButtonState("tsp");
            gui.canvas.setIsEnabled(true);

        }else {
            gui.canvas.setRadioButtonState("");
            gui.canvas.setIsEnabled(false);
            for(int i=0;i<gui.buttons.length;++i) {
                gui.buttons[i].setEnabled(true);
            }
            gui.canvas.repaint();
        }
    }

    /*
     * Disables all the radio buttons except for the
     * one chosen
     * @param index the radio button that has been clicked
     */
    private void disableUnselectedButtons(int index) {
        gui.canvas.setIsEnabled(false);
        for(int i=0;i<gui.buttons.length;++i) {
            if(i==index)continue;
            gui.buttons[i].setEnabled(false);
        }
        gui.canvas.setEndpt1(null);
        gui.canvas.setEndpt2(null);
    }


}