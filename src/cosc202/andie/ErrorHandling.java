package cosc202.andie;

import javax.swing.JOptionPane;

public class ErrorHandling{

    public void badSaveError(){

        int okayOption = JOptionPane.showConfirmDialog(null, null, "You can't save a file under this type/name. Please Try again.", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
        
        
        if(okayOption == JOptionPane.OK_OPTION){

            return;

        }

    }

    public void wrongFileTypeError(){



    }

}