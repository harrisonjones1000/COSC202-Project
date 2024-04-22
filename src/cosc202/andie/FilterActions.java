package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    
    /*Initializing the resource bundle by getting the resource bundle from the Andie class */
    private ResourceBundle lan = Andie.lan;
    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(lan.getString("mean_filter"), null, lan.getString("mean_filter_desc"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenFilterAction(lan.getString("sharpen_filter"), null, lan.getString("sharpen_filter_desc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new GaussianBlurFilterAction(lan.getString("gaussian_blur_filter"), null, lan.getString("gaussian_blur_filter_desc"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new MedianFilterAction(lan.getString("median_blur_filter"), null, lan.getString("median_blur_filter_desc"), Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new EmbossFilterAction("Emboss filter", null, "Apply an Emboss filter", Integer.valueOf(KeyEvent.VK_E)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(lan.getString("filter"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius and if they want a negative offset, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;
            boolean negOffSet = false;

            //2 Pop-up dialog boxes to ask for the radius value and Negative Offset respectfully.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            Object[] options = {lan.getString("ok"), lan.getString("cancel")};
            Object[] negOptions = {"Yes","No (recomended)"};
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, lan.getString("mean_filter_title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            int negOption = JOptionPane.showOptionDialog(null, "Do you want to adjust for negatives in the filter?", "Negative Adjustment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, negOptions, negOptions[1]);
            // Check the return value from the dialog boxes.
            if (option == JOptionPane.CANCEL_OPTION) {

                return;
                
            } else if (option == JOptionPane.OK_OPTION) {

                radius = radiusModel.getNumber().intValue();

            }

            if (negOption == JOptionPane.YES_OPTION) {

                negOffSet = true;
                
            }
            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius,negOffSet));
            target.repaint();
            target.getParent().revalidate();
        }

    }
    /**
     * <p>
     * Action to blur an image with a sharpen filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It prompts the user if they want a negative offset, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            //Pop-up dialog box to ask for Negative Offset.
            boolean negOffSet = false;
            Object[] negOptions = {"Yes","No (recomended)"};
            int negOption = JOptionPane.showOptionDialog(null, "Do you want to adjust for negatives in the filter?", "Negative Adjustment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, negOptions, negOptions[1]);
            // Check the return value from the dialog box.
            if (negOption == JOptionPane.YES_OPTION) {

                negOffSet = true;
                
            }
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter(negOffSet));
            target.repaint();
            target.getParent().revalidate();
        }

    }
    public class GaussianBlurFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new GaussianBlur-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        GaussianBlurFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianBlurFilterAction is triggered.
         * It prompts the user for a filter radius and if they want a negative offset, then applies an appropriately sized {@link GaussianBlurFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the radius/negOffSet - ask the user.
            int radius = 1;
            boolean negOffSet = false;

            //2 Pop-up dialog boxes to ask for the radius value and Negative Offset respectfully.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            Object[] options = {lan.getString("ok"), lan.getString("cancel")};
            Object[] negOptions = {"Yes","No (recomended)"};
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, lan.getString("mean_filter_title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            int negOption = JOptionPane.showOptionDialog(null, "Do you want to adjust for negatives in the filter?", "Negative Adjustment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, negOptions, negOptions[1]);
            // Check the return value from the dialog boxes.
            if (option == JOptionPane.CANCEL_OPTION) {

                return;
                
            } else if (option == JOptionPane.OK_OPTION) {

                radius = radiusModel.getNumber().intValue();

            }

            if (negOption == JOptionPane.YES_OPTION) {

                negOffSet = true;
                
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianBlurFilter(radius,negOffSet));
            target.repaint();
            target.getParent().revalidate();
        }

    }
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new MedianBlur-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the radius/negOffSet - ask the user.
            int radius = 1;
            boolean negOffSet = false;

            //2 Pop-up dialog boxes to ask for the radius value and Negative Offset respectfully.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            Object[] options = {lan.getString("ok"), lan.getString("cancel")};
            Object[] negOptions = {"Yes","No (recomended)"};
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, lan.getString("mean_filter_title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            int negOption = JOptionPane.showOptionDialog(null, "Do you want to adjust for negatives in the filter?", "Negative Adjustment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, negOptions, negOptions[1]);
            // Check the return value from the dialog boxes.
            if (option == JOptionPane.CANCEL_OPTION) {

                return;
                
            } else if (option == JOptionPane.OK_OPTION) {

                radius = radiusModel.getNumber().intValue();

            }

            if (negOption == JOptionPane.YES_OPTION) {

                negOffSet = true;
                
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius,negOffSet));
            target.repaint();
            target.getParent().revalidate();
        }

    }
    /**
     * <p>
     * Action to blur an image with an emboss filter.
     * </p>
     * 
     * @see EmbossFilter
     */
    public class EmbossFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new emboss-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilterAction is triggered.
         * It prompts the user, which filter want they want and if they want a negative offset, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            //Pop-up dialog boxes to ask for which filter to use and Negative Offset.
            boolean negOffSet = false;
            Object[] options = {"Left", "Up Left", "Up", "Up Right", "Right", "Down Right", "Down", "DownLeft"};
            Object[] negOptions = {"Yes (recomended)","No"};
            int option = JOptionPane.showOptionDialog(null, "Which filter do you want to use?", "Emboss filters", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            int negOption = JOptionPane.showOptionDialog(null, "Do you want to adjust for negatives in the filter?", "Negative Adjustment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, negOptions, negOptions[1]);
            // Check the return value from the dialog boxes.
            if (negOption == JOptionPane.YES_OPTION) {

                negOffSet = true;
                
            }
            // Create and apply the filter
            target.getImage().apply(new EmbossFilter(negOffSet, option));
            target.repaint();
            target.getParent().revalidate();
        }

    }
}
