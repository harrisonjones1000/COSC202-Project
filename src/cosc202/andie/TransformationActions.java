package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * <p>
 * Actions provided by the Transformations menu.
 * </p>
 * 
 * <p>
 * The Transformation menu provides transformations to be applied to an image
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Harrison Jones
 * @version 1.0
 */
public class TransformationActions{

    protected ArrayList<Action> actions;
    ResourceBundle lan = Andie.lan;

     /**
     * <p>
     * Create a set of Transformation menu actions
     * </p>
     */
    public TransformationActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction(lan.getString("resize"), null, lan.getString("resize_desc"), Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAction(lan.getString("rotate"), null, lan.getString("rotate_desc"), Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new FlipAction(lan.getString("flip"), null, lan.getString("flip_desc"), Integer.valueOf(KeyEvent.VK_F)));
    }

    /**
     * <p>
     * Create a menu containing the list of Transformation actions.
     * </p>
     * 
     * @return The Transfromation menu UI element.
     */
    public JMenu createJMenu() {
        JMenu transMenu = new JMenu(lan.getString("transformations"));

        transMenu.add(new JMenuItem(actions.get(0)));
        transMenu.add(new JMenuItem(actions.get(1)));
        transMenu.add(new JMenuItem(actions.get(2)));

        return transMenu;
    }

    /**
     * <p>
     * Action to resize an image
     * </p>
     * 
     * @see Resize
     */
    public class ResizeAction extends ImageAction{
        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResiseAction is triggered.
         * It prompts the user to select a scalefactor, then applies the specified {@link Resize}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            double scaleFactor = 1;

            //Pop-up dialog box will take scale factors between .1 and 10, with a step size of .1
            SpinnerNumberModel scaleFactorModel = new SpinnerNumberModel(1.0, .1, 10, .1);
            JSpinner scaleFactorSpinner = new JSpinner(scaleFactorModel);
            Object[] options = {lan.getString("ok"), lan.getString("cancel")};
            int option = JOptionPane.showOptionDialog(null, scaleFactorSpinner, lan.getString("resize_action_title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scaleFactor = scaleFactorModel.getNumber().doubleValue();
            }

            // Create and apply the filter, also changes new preferred size.
            target.getImage().apply(new Resize(scaleFactor));
            target.setPreferredSize(target.getPreferredSize());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image
     * </p>
     * 
     * @see Rotate
     */
    public class RotateAction extends ImageAction{
        /**
         * <p>
         * Create a new rotate action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the rotate action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateAction is triggered.
         * It prompts the user to select the type of rotation, then applies the specified {@link Rotate}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            Object[] options = {lan.getString("rotate_90_r"), lan.getString("rotate_90_l"), lan.getString("rotate_180"), lan.getString("cancel")};
            int n = JOptionPane.showOptionDialog(null, null, lan.getString("select"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);

            if (n == 3) {
                return;
            }

            target.getImage().apply(new Rotate(n)); 
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to flip an image
     * </p>
     * 
     * @see Flip
     */
    public class FlipAction extends ImageAction{
        /**
         * <p>
         * Create a new flip action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipAction is triggered.
         * It prompts the user to select the type of flip, then applies the specified {@link Flip}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            boolean flipX = true;
            Object[] options = {lan.getString("flip_hori"), lan.getString("flip_vert"), lan.getString("cancel")};
            int n = JOptionPane.showOptionDialog(null, null, lan.getString("select"),JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

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