package cosc202.andie;

import java.awt.image.*;

public class Rotate implements ImageOperation, java.io.Serializable {
    
    Rotate(){}

    public BufferedImage apply(BufferedImage input){
        return input;
    }
}
