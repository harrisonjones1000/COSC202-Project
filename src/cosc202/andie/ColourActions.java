package cosc202.andie;
import cosc202.andie.Andie;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {

    /*Get the resource bundle from the Andie class */
    private ResourceBundle lan = Andie.lan;

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;
    
    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(lan.getString("greyscale"), null, lan.getString("greyscale_description"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new ImageInversionAction(lan.getString("image_inversion"), null, lan.getString("image_inversion_desc"), Integer.valueOf(KeyEvent.VK_I)));
        actions.add(new ColorChannelCycleAction(lan.getString("color_chanel_cycle"), null, lan.getString("color_chanel_cycle_desc"), Integer.valueOf(KeyEvent.VK_C)));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(lan.getString("colour"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }
        /**
     * <p>
     * Action to convert an image to its inverse.
     * </p>
     * 
     * @see ImageInversion
     */

    public class ImageInversionAction extends ImageAction {

        /**
         * <p>
         * Create a new image-inversion action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ImageInversionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the image-inversion action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ImageInversionAction is triggered.
         * It changes the image to its inverse.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageInversion());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to convert an image to its inverse.
     * </p>
     * 
     * @see ColorChannelCycle
     */

     public class ColorChannelCycleAction extends ImageAction {

        /**
         * <p>
         * Create a new color-channel-cycle action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ColorChannelCycleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the color-channel-cycle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ColorChannelCycleAction is triggered.
         * It swaps the color channels of the image in a direction of user choice.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            String[] directions = {"r->g->b->r","r<-g<-b<-r","Swap green and blue","Swap red and green","Swap red and blue", lan.getString("cancel")};
            int option = JOptionPane.showOptionDialog(null, lan.getString("color_chanel_cycle_message"), lan.getString("color_chanel_cycle_title"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, directions, null);
            System.out.println(option);
            if (option == 6) {
                return;
            }
            target.getImage().apply(new ColorChannelCycle(option));
            //target.getImage().apply(new ColorChannelCycle(true)); //Check if better to set a variable
            //Or call two methods
            //Currently setting a variable.
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
