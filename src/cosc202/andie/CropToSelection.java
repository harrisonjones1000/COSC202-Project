package cosc202.andie;

import java.awt.image.*;
import java.awt.Rectangle;

/**
 * <p>
 * ImageOperation to crop an image to a specified selected rectangle
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Harrison Jones
 * @version 1.0
 */
public class CropToSelection implements ImageOperation, java.io.Serializable {
    
    private Rectangle rectangle;

    /**Constructs a CropToSelectionn operation for a given rectangle*/
    CropToSelection(Rectangle rectangle){
        this.rectangle = rectangle;
    }
    /**
     * <p>
     * Apply a Crop operation to an image.
     * </p>
     * 
     * @param input The image to apply the Flip to.
     * @return The resulting cropped image.
     */
    public BufferedImage apply(BufferedImage input){
        if(rectangle==null)return input;

        BufferedImage output = new BufferedImage((int)rectangle.getWidth(), (int)rectangle.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int x=0; x<rectangle.getWidth(); x++){
            for(int y=0; y<rectangle.getHeight(); y++){
                output.setRGB(x,y,input.getRGB(x+(int)rectangle.getX(), y+(int)rectangle.getY()));
            }
        }
        return output;
    }
}

