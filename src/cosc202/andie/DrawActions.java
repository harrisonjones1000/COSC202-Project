package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DrawActions {

    private boolean drawing;

    public DrawActions(){
        this.drawing = false;
    }

    public JMenu createJMenu(){
        JMenu drawMenu = new JMenu("Draw");

        drawMenu.add(new JCheckBoxMenuItem(new DrawAction("Draw On",null,"Draw On",null)));

        return drawMenu;
    }

    /**
     * <p>
     * Action when click Draw checkbox
     * </p>
     */
    public class DrawAction extends ImageAction{
        /**
         * <p>
         * Create a new DrawAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            drawing = !drawing;
        }
    }
}
