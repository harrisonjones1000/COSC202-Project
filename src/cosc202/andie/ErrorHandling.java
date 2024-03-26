package cosc202.andie;

import javax.swing.JFileChooser;
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
    public static void badSaveError(){

        int okayOption = JOptionPane.showConfirmDialog(null, 
                         "You can't save this file.  Either: \n - \tThere is no file to save." 
                       + "\n - \tAn attempt has been made to save the file as an incorrect image" 
                       + "file type. \n\nPlease try again.", 
                        "Invalid Save Type", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        
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

        int okayOption = JOptionPane.showConfirmDialog(null,
                         "You can't open a file of this type.", 
                         "Invalid File Type", 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

}