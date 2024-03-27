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
    
    /*Initializing the resource bundle by getting the resource bundle from the Andie class */
    private ResourceBundle lan = Andie.lan;

    /**A list of Language options stored as an ArrayList. */
    protected ArrayList<String> languages;
    
    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageActions(){
        languages  = new ArrayList<String>();
        //will add actions later.
        languages.add("English");
        languages.add("Māori");
    }
    /**
     * <p>
     * Create a menu of language preferrences, when preferences are updated the program will close.
     * </p>
     * 
     * @return The edit menu UI element
     */
    public JMenu createMenu(){
        JMenu langMenu = new JMenu(lan.getString("language"));

        for(String language : languages){
            JMenuItem item = null;
            item = new JMenuItem(language);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae){
                    /*Since the language object is private we use the accessor method
                      getLanguage() to get the object then modifiy it with the new 
                      language, using the setLanguage(String preferredLanguage) method.
                    */
                    Andie.getLanguage().setLanguage(language);
                    //close the program 
                    System.exit(0);
                }
            });
            langMenu.add(item);
        }
        return langMenu;
    }


}