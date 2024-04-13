package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to resize the image to a specified scale factor
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image
 * @author Harrison Jones
 * @version 1.0
 */
public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * The factor the image will be resized as
     */
    private double scaleFactor = .5;
    private Rectangle rectangle;
    
    /**
     * Constructs a Resize operation with the given {@link scaleFactor}.
     * 
     * @param scaleFactor The scaleFactor of the newly constructed Resize.
     */
    Resize(double scaleFactor, Rectangle rectangle) {
        this.scaleFactor = scaleFactor;    
        this.rectangle = rectangle;
    }
    /**
     * <p>
     * Apply a Resize operation to an image.
     * </p>
     * 
     * <p>
        The size of the image is the size of the original image multiplied by the supplied scale factor.
        This particular image scaler prioritieses image smoothness over speed.
     * </p>
     * 
     * @param input The image to apply the Flip to.
     * @return The resulting flipped image.
     */
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