package cosc202.andie;

import java.awt.image.*;

public class Flip implements ImageOperation, java.io.Serializable {
    
    Flip(){}

    public BufferedImage apply(BufferedImage input){
        return input;
    }
}

