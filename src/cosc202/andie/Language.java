package cosc202.andie;
import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


public class Language {

    /*I'll keep the prefs as a datafeld */
    public Preferences prefs;
    ResourceBundle bundle;

    /*The constructor creates the object Language, which is set to default to English.
     * @param void
     * @return void
     */
    public Language(){
        //run a few opertations to find the right language
        prefs = Preferences.userNodeForPackage(Language.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), 
                prefs.get("country", "NZ")));
        bundle = ResourceBundle.getBundle("MessageBundle");

    }
    /*The method sets the preferred language
     * @param String preferred language
     * @return void
     */
    // Note the main issue is here? They may be an issue with the logic of my if statements.
    public void setLanguage(String langauge){
        System.out.println("String language = " + langauge); 

        //Setting the language preferences based on String language
        try{
            if(langauge == "English"){
                //Andie.frame.dispose();
                prefs.put("language", "en");
                prefs.put("country", "NZ");
                Andie.frame.repaint();
                
            }else if(langauge == "Māori"){
                //Andie.frame.dispose();
                prefs.put("language", "mi");
                prefs.put("country", "NZ");
                Andie.frame.repaint();
                
            }

            
            
            //bundle = ResourceBundle.getBundle("MessageBundle");
        }catch(NoSuchElementException ea){
            System.out.println("Languages preferences couldn't be updated\n" + ea.toString());
        }
        //Testing to see if prefs have been updated.
        System.out.println("Current Language: " + getCurrentLanguage());
    }

    /*Method that returns the current language 
     * @parm void
     * @return String representation of the current Language
    */
    public String getCurrentLanguage(){
        return bundle.getString("set_language");
    }

    /*The method returns the current Language resource bundle
     * @param void
     * @return ResourceBundle bundle;
     */
    public ResourceBundle getLanBundle(){
        return ResourceBundle.getBundle("MessageBundle");
    }

}
