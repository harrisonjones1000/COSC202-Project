package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * The factor the image will be resized as
     */
    private double scaleFactor = .5;
    
    /**
     * Constructs a Resize  with the given scaleFactor.
     * 
     * @param scaleFactor The scaleFactor of the newly constructed Resize.
     */
    Resize(double scaleFactor) {
        this.scaleFactor = scaleFactor;    
    }

    public BufferedImage apply(BufferedImage input){
        
        int newWidth = (int)(scaleFactor*input.getWidth());
        int newHeight = (int)(scaleFactor*input.getHeight());

        Image img = input.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH);

        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        
        return output;
        
    }

}
