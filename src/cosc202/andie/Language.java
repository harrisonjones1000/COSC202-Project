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
        System.out.println("The Language class is accessed.");
        //run a few opertations to find the right language
        prefs = Preferences.userNodeForPackage(Language.class);
        System.out.println("prefs assigned");
        Locale.setDefault(new Locale(prefs.get("language", "mi"), 
                prefs.get("country", "NZ")));
        System.out.println("Locale set");
        bundle = ResourceBundle.getBundle("MessageBundle");
        System.out.println("bundle object assigned");
        /*Testing if the bundle is correctly initialized */
        System.out.println(bundle.getString("file"));


    }
    /*The method sets the preferred language
     * @param String preferred language
     * @return void
     */
    public void setLanguage(String langauge){
        try{
            if(langauge.equals("English") || langauge.equals("english")){
                prefs.put("language", "en");
            }
            prefs.put("country", "mi");
        }catch(NoSuchElementException ea){
            System.out.println("Languages preferences couldn't be updated\n" + ea.toString());
        }
    }
    public ResourceBundle getLanBundle(){
        return bundle;
    }

}
