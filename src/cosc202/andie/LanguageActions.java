package cosc202.andie;

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
public class LanguageActions {

    ResourceBundle lan = Andie.language.getLanBundle();

    /**A list of Language options stored as an ArrayList. */
    protected ArrayList<String> actions;
    
    /**
     * <p>
     * Create a list of language options.
     * </p>
     */
    public LanguageActions(){
        actions  = new ArrayList<String>();
        //will add actions later.
        actions.add("English");
        actions.add("Māori");
    }

    /**
     * <p>
     * Create a menu of language preferrences
     * </p>
     * 
     * @return The edit menu UI element
     */
    public JMenu createMenu(){
        JMenu langMenu = new JMenu(lan.getString("language"));

        for(String action : actions){
            JMenuItem item = null;
            item = new JMenuItem(action);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae){
                    //System.out.println("Button clicked");
                    Andie.language.setLanguage(action);
                }
            });
            langMenu.add(item);
        }
        return langMenu;
    }


}