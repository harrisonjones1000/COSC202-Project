package cosc202.andie;

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
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {
    
    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /*retrieving the resources object from the Language object which stores the preferences. */
    ResourceBundle lan = Andie.language.getLanBundle(); 
    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(lan.getString("open"), null, lan.getString("open_desc"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction(lan.getString("save"), null, lan.getString("save_desc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(lan.getString("save_as"), null, lan.getString("save_as_desc"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new ExportImageAction(lan.getString("export"), null, lan.getString("export_desc"), null));
        actions.add(new FileExitAction(lan.getString("exit"), null, lan.getString("exit_desc"), Integer.valueOf(0)));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(lan.getString("file"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);

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
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);

        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {

                target.getImage().save();  

            } catch (Exception ex) {

                ErrorHandling.badSaveError();
            }

        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);

                } catch (Exception ex) {

                    ErrorHandling.badSaveError();

                }
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class ExportImageAction extends ImageAction {


        /**
         * <p>
         * Create a new export file action
         * </p>
         * 
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ExportImageAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

         /**
         * <p>
         * Callback for when export image action is performed.
         * </p>
         * 
         * <p>
         * This method is called whenever the ExportImageAction is triggered 
         * it prompts the user to enter a fileName in a folder where the image will be 
         * saved.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(fileChooser);

            if(result ==JFileChooser.APPROVE_OPTION){
                try{
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().exportImage(imageFilepath);
                }catch(Exception ex){
                    System.out.println(ex.toString());
                    System.exit(1);
                }
            }
        }
    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends ImageAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
         
        /**  
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program if the image opened has had no changes made,
         * if changes have been made, asks the user to save, calling the 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {

            /* If the image has had no changes made when the exit button is pushed */
            if(!Andie.imagePanel.image.hasChanged()){

                /* Exit the program*/ 
                System.exit(0);

            }
            /* If the image has had a change made when the exit button is pushed */
            else{

                /* Create a dialogue box with a yes no option to ask if the user
                   wants to save the image */
                int okayOption = JOptionPane.showConfirmDialog(null,
                "Do you wish to save this image?", 
                "Save Image", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                
                /* If the user presses yes (wants to save) */
                if(okayOption == JOptionPane.YES_OPTION){
                    
                    /* Create the file chooser window, and save dialogue */
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(target);
                    
                    /* If the user presses the save button */
                    if (result == JFileChooser.APPROVE_OPTION) {
                        
                        /* Try to save the image with name given */
                        try {
        
                            String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            target.getImage().saveAs(imageFilepath);

                    /* If invalid image type, show corresponding error */
                        } catch (Exception ex) {
        
                            ErrorHandling.badSaveError();
        
                        }
        
                    }
        
                }
                
                /* If the user presses no (doesn't want to save) */
                else if(okayOption == JOptionPane.NO_OPTION){
                    
                    /* Exit the program */
                    System.exit(0);
        
                }
        
            }


        }

    }

}
