package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * <p>
 * Actions provided by the Language menu.
 * </p>
 * 
 * <p>
 * The Language menu contains actions that affects the language of ANDIE.
 * The supported languages consists of English and Maori, with each having their own language file.
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
    /**The current language bundle of ANDIE*/
    ResourceBundle lan = Andie.language.getLanBundle();

    /**A list of Language options stored as an ArrayList. */
    protected ArrayList<String> actions;
    
    /**
     * <p>
     * Create a set of Language menu actions.
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
                    //close the program 
                    System.exit(0);
                }
            });
            langMenu.add(item);
        }
        return langMenu;
    }


}