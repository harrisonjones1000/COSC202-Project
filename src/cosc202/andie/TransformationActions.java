package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TransformationActions{

    protected ArrayList<Action> actions;

    public TransformationActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction("Resize", null, "Resizes image", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAction("Rotate", null, "Rotates image clockwise", Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new FlipAction("Flip", null, "Flips image", Integer.valueOf(KeyEvent.VK_F)));
    }

    public JMenu createJMenu() {
        JMenu fileMenu = new JMenu("Transformations");

        fileMenu.add(new JMenuItem(actions.get(0)));
        fileMenu.add(new JMenuItem(actions.get(1)));
        fileMenu.add(new JMenuItem(actions.get(2)));

        return fileMenu;
    }

    public class ResizeAction extends ImageAction{
        
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            double scaleFactor = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel scaleFactorModel = new SpinnerNumberModel(1.0, .1, null, .1);
            JSpinner scaleFactorSpinner = new JSpinner(scaleFactorModel);
            int option = JOptionPane.showOptionDialog(null, scaleFactorSpinner, "Enter scale factor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scaleFactor = scaleFactorModel.getNumber().doubleValue();
            }

            target.getImage().apply(new Resize(scaleFactor));

            target.setPreferredSize(target.getPreferredSize());

            target.repaint(); //repaints target with new current image
            target.getParent().revalidate();
        }
    }

    public class RotateAction extends ImageAction{
        
        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            Object[] options = {"Rotate 90 degrees right", "Rotate 90 degrees left", "Rotate 180 degrees", "Cancel"};
            int n = JOptionPane.showOptionDialog(null, null, "Select", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);

            if (n == 3) {
                return;
            }


            target.getImage().apply(new Rotate(n)); 
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class FlipAction extends ImageAction{
        
        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            boolean flipX = true;
            Object[] options = {"Flip Horizontal", "Flip Vertical", "Cancel"};
            int n = JOptionPane.showOptionDialog(null, null, "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (n == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (n == JOptionPane.YES_OPTION) {
                flipX = true;
            }else if (n == JOptionPane.NO_OPTION) {
                flipX = false;
            }
           
            target.getImage().apply(new Flip(flipX));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}