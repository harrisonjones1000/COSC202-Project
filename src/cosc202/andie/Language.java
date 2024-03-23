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
    // Note the main issue is here? They may be an issue with the logic of my if statements.
    public void setLanguage(String langauge){
        /*This statement for my debugging prints out the expected language when the user clicks on 
          a specific language option
          */
        System.out.println("String language = " + langauge); 

        //using the param string to perform some opertations to update the language preferences
        try{
            if(langauge == "English"){
                prefs.put("language", "en");
                System.out.println("Prefs updated to English");
            }
            if(langauge == "Māori"){
                prefs.put("language", "mi");
                System.out.println("Prefs updated to Māori");
            }

            prefs.put("country", "NZ");
        }catch(NoSuchElementException ea){
            System.out.println("Languages preferences couldn't be updated\n" + ea.toString());
        }
        System.out.println("Update to " + bundle.getString("set_language"));
    }
    public ResourceBundle getLanBundle(){
        return bundle;
    }

}
