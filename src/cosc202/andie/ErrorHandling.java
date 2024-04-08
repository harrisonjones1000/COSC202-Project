package cosc202.andie;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class ErrorHandling{

/*********************************************************************/
/**                        badSaveError method                      **/
/*********************************************************************/ 
    /**<p>
     * Method for catching runtime errors where a user attempts to save 
     * a file that is either an incorrect type, or no file at all. 
     * </p>
     * 
     * <p>
     * Upon attempting to save an invalid file, will alert the user as
     * to the error of their action, and upon clicking the okay button
     * will continue with the program. 
     * </p>
     * 
     */
    
    /*Initializing the resource bundle by getting the resource bundle from the Andie class */
    private static ResourceBundle lan = Andie.lan;
    public static void badSaveError(){

        Object[] options = {lan.getString("ok")};
        int okayOption = JOptionPane.showOptionDialog(null, 
                         lan.getString("save_error_message"), 
                        lan.getString("save_error_title"), 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }


/*********************************************************************/
/**                      wrongFileTypeError method                  **/
/*********************************************************************/ 
    /**<p>
     * Method for catching runtime errors where a user attempts to open 
     * an incorrect file type.
     * </p>
     * 
     * <p>
     * Upon Attempting to open an invalid file type, will alert the 
     * user that the type of file selected cannot be opened, and upon
     * pressing the okay button will return to the program.
     * </p>
     * 
     */

    public static void wrongFileTypeError(){
        
        int okayOption = JOptionPane.showOptionDialog(null,
                         lan.getString("wrong_file_type_error_message"), 
                         lan.getString("wrong_file_type_error_title"), 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[]{lan.getString("ok")}, null);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

/*********************************************************************/
/**                     exportLocationError method                  **/
/*********************************************************************/ 
    /**<p>
     * Method for catching runtime errors where a user attempts to export
     * a file to the wrong location.
     * </p>
     * 
     * <p>
     * Upon attempting to export a file to an incorrect location, will 
     * alert the user and ask to save in a different location.
     * </p>
     * 
     */

     public static void exportLocationError(){
        
        int okayOption = JOptionPane.showOptionDialog(null,
                         lan.getString("export_location_error_message"), 
                         lan.getString("export_location_error_title"), 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[]{lan.getString("ok")}, null);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

/*********************************************************************/
/**                      cannotUndoMessage method                   **/
/*********************************************************************/ 
    /**<p>
     * Method for catching runtime error for empty stack, where the user 
     * cannot undo as there are no items in the ops stack. Alerts
     * the user that they can't undo.
     * </p>
     * 
     * <p>
     * Attemmpting to undo on an empty image panel, or unchanged image 
     * causes an error message pop-up, returns nothing.
     * </p>
     * 
     */

    public static void cannotUndoMessage(){
        
        int okayOption = JOptionPane.showOptionDialog(null,
                         lan.getString("cannot_undo_message"), 
                         lan.getString("cannot_undo_title"), 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[]{lan.getString("ok")}, null);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

/*********************************************************************/
/**                      cannotRedoMessage method                   **/
/*********************************************************************/ 
    /**<p>
     * Method for catching runtime error for empty stack, where the user 
     * cannot redo as there are no items in the redoOps stack. Alerts
     * the user that they can't redo.
     * </p>
     * 
     * <p>
     * Attemmpting to redo after the most current change has been redone 
     * causes an error message pop-up, returns nothing.
     * </p>
     * 
     */

     public static void cannotRedoMessage(){
        
        int okayOption = JOptionPane.showOptionDialog(null,
                         lan.getString("cannot_redo_message"), 
                         lan.getString("cannot_redo_title"), 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[]{lan.getString("ok")}, null);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

}