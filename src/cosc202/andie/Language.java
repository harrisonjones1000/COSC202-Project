package cosc202.andie;
import java.util.*;
import java.util.prefs.Preferences;


public class Language {

    /*I'll keep the prefs as a datafeld */
    private Preferences prefs;

    public ResourceBundle bundle;

    private Language language;

    //The constructor creates the object Language, which is set to default to English.
    /**Creates a new Language class
     * <p>
     * A new Language class will obtain the local language preferences set by the user in their last session, 
     * or the local default if no preference has been set.
     * </p>
     */
    public Language(){
        prefs = Preferences.userNodeForPackage(Language.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), 
                prefs.get("country", "NZ")));
        bundle = ResourceBundle.getBundle("MessageBundle");
    }
    /*<p>
     * Sets the preferred language of the user.
     *<p>
     * 
     * Tries to set the language of the program to the one chosen by the
     * user in the language menu.
     * @param langauge The language the user wishes to set as their default.
     */
    // Note the main issue is here? They may be an issue with the logic of my if statements.
    public void setLanguage(String langauge){
        
        //Setting the language preferences based on String language
        
        try{
            if(langauge.equalsIgnoreCase("English")){
                prefs.put("language", "en");
                prefs.put("country", "NZ");
                
            }else if(langauge.equalsIgnoreCase("Māori")){
                prefs.put("language", "mi");
                prefs.put("country", "NZ");
            }
            Andie.createPopupPanel(bundle.getString("lanPopUp_title"), bundle.getString("lanPopUp_message"), "information");
        }catch(NoSuchElementException ea){
            System.out.println("Languages preferences couldn't be updated.\n" + ea.toString());
        }
    }

    /**Method that returns the current language 
     * @return String representation of the current Language
    */
    public String getCurrentLanguage(){
        return bundle.getString("set_language");
    }

    /**The method returns the current Language resource bundle
     * @return ResourceBundle consisting of the language bundle;
     */
    public ResourceBundle getLanBundle(){
        return ResourceBundle.getBundle("MessageBundle");
    }
    /**
     * <p>
     * Acessor method for the Language language object
     * </p>
     * @return Language language object
     */
    public Language getLanguage(){
        return language;
    }
}
