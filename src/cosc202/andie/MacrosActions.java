package cosc202.andie;

//NEED TO GO AND CHANGE ALL THE COMMENTS - Calan
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class MacrosActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /*
     * Initializing the resource bundle by getting the resource bundle from the
     * Andie class
     */
    private ResourceBundle lan = Andie.lan;
    Action startStop;
    private boolean active;
    /**
     * <p>
     * Create a set of Macros menu actions.
     * </p>
     */
    public MacrosActions() {
        actions = new ArrayList<Action>();
        startStop = new MacrosStartStopAction("Start Recoding", null,
        "Start/Stop Macros Recording", null);
        actions.add(new MacrosOpenAction("Open Macros", null,
                "Open External Macros", null));
        actions.add(new MacrosSaveAsAction("Save Macros", null,
                "Save Recorded Macros", null));
        actions.add(startStop);//Make it say Stop recording when already started
        actions.add(new MacrosClearAction("Clear Macros", null,
                "Clear Macros Recording", null));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Macros"/* lan.getString("macrps") */);

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#applyMacros(String)
     */
    public class MacrosOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacrosOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Apply Macros"/* lan.getString("open") */); // found this method at
                                                                                   // https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JFileChooser.html#showOpenDialog(java.awt.Component)
            int result = fileChooser.showOpenDialog(target);
            // See if you can make it say Apply Macros on the button
            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().applyMacros(imageFilepath);

                } catch (Exception ex) {

                    ErrorHandling.wrongFileTypeError();

                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveMacrosAs(String)
     */
    public class MacrosSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacrosSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            //Ensure that user cannot save empty macros
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(lan.getString("save"));// Save macros
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveMacrosAs(imageFilepath);

                } catch (Exception ex) {

                    ErrorHandling.badSaveError();

                }
            }
        }

    }

    public class MacrosStartStopAction extends ImageAction {
        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacrosStartStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            active = !active;
            if (!active) {
                //Figure out how to dynamically change name
                
            } 
            target.getImage().startStopMacros();
        }
    }

    public class MacrosClearAction extends ImageAction {
        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacrosClearAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().clearMacros();
        }
    }
}