package cosc202.andie;

import javax.swing.JOptionPane;

public class ErrorHandling{

    public static void badSaveError(){

        int okayOption = JOptionPane.showConfirmDialog(null, 
                         "You can't save a file under this type/name. Please Try again.", 
                         "Invalid Save Type", 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        
        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

    /******************************************************************/
    /**                     wrongFileTypeError method                **/
    /******************************************************************/ 
    /**<p>
     * Method for catching errors where a user attempts to open an incorrct file type.
     * </p>
     * 
     * <p>
     * Upon Attempting to open an invalid file type, will 
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

    public static void noFileToSave(){

        int okayOption = JOptionPane.showConfirmDialog(null,
                         "You have tried to save a non-existent file.", 
                         "File does not exist", 
                         JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }



}