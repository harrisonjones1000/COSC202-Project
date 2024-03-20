package cosc202.andie;

import java.awt.image.*;

public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * The factor the image will be resized as
     */
    private double scaleFactor;
    
    /**
     * Constructs a Resize  with the given scaleFactor.
     * 
     * @param scaleFactor The scaleFactor of the newly constructed Resize.
     */
    Resize(double scaleFactor) {
        this.scaleFactor = scaleFactor;    
    }

    public BufferedImage apply(BufferedImage input){
        /* 
        int newWidth = (int)(scaleFactor*input.getWidth());
        int newHeight = (int)(scaleFactor*input.getHeight());
        BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        return result;
        */
        return input;
    }

}
