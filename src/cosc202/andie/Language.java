package cosc202.andie;
import java.util.*;
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
    public void setLanguage(String langauge){
        try{
            if(langauge.equals("English") || langauge.equals("english")) prefs.put("language", "en");
               
            else if(langauge.equals("Māori")) prefs.put("language", "mi");

            prefs.put("country", "mi");
        }catch(NoSuchElementException ea){
            System.out.println("Languages preferences couldn't be updated\n" + ea.toString());
        }
        System.out.println("Update to " + bundle.getString("set_language"));
    }
    public ResourceBundle getLanBundle(){
        return bundle;
    }

}
