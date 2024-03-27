package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 * 
 * 
 */
public class Andie {

    /*I declare the Language class in this class, Which is public so all the classes can access it.*/
    private static Language language = new Language();
    public static ResourceBundle lan = language.getLanBundle();
    private static JFrame frame;
    public static ImagePanel imagePanel = new ImagePanel();

    /**
     * 
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggered via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Set up the main GUI frame
        frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        //Testing menu items, UI logic
        LanguageActions lanActions = new LanguageActions();
        menuBar.add(lanActions.createMenu());

        //Transformations: resize, flip, rotate
        TransformationActions transActions = new TransformationActions();
        menuBar.add(transActions.createJMenu());
        
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * <p>
     *  The method creates a Popup panel which within the JFrame frame, 
     *  it dynamically renders JOptionPane based on a number of parameters
     * </p>
     * <p>
     * Documentation for JOptionPan: https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JOptionPane.html#INFORMATION_MESSAGE
     * </p>
     *
     * @param String title, String message, String category 
     * @return vod
    */
    public static void createPopupPanel(String title, String message, String category){
        //dynamically insert optionType based on the string category, and also the button text based on the language
        switch (category) {
            case "information":
                JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION, 
                                            JOptionPane.INFORMATION_MESSAGE, null,
                                            new Object[]{lan.getString("ok")}, null);
                
                break;
            case "error":
            JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION, 
                JOptionPane.WARNING_MESSAGE, null,
                new Object[]{lan.getString("ok")}, null); 
            default:
                break;
        }
    }
    public static Language getLanguage(){
        return language;
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */

     
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}

