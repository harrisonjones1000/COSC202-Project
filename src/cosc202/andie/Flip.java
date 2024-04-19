package cosc202.andie;

import java.awt.image.*;
import java.awt.Rectangle;

/**
 * <p>
 * ImageOperation to flip an image a specified way.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Harrison Jones
 * @version 1.0
 */
public class Flip implements ImageOperation, java.io.Serializable {
    
    /**Controls direction of flip operation, horizontally if true and vertically if false*/
    private boolean flipX;
    private Rectangle rectangle;

    /**Constructs a Flip operation with a given direction
     * <p>
     * This class supports image flipping in the horizontal and vertical planes. 
     * The direction of this operation depends on the given boolean.
     * 
     * @param flipX Indicates the direction of the flip operation.
     */
    Flip(boolean flipX, Rectangle rectangle){
        this.flipX = flipX;
        this.rectangle = rectangle;
    }
    /**
     * <p>
     * Apply a Flip operation to an image.
     * </p>
     * 
     * <p>
        The Flip operation is controlled by an if statement in code, controlled by {@link flipX}.

     * </p>
     * 
     * @param input The image to apply the Flip to.
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input){
        int width, height, x0, y0, x1, y1;
        if(rectangle!=null){
            width = (int)rectangle.getWidth();
            height = (int)rectangle.getHeight();
            x0 = (int)rectangle.getX();
            y0 = (int)rectangle.getY();
            x1 = x0 + width;
            y1 = y0 + height;
        }else{
            width = input.getWidth();
            height = input.getHeight();
            x0 = 0;
            y0 = 0;
            x1 = width;
            y1 = height;
        }
        
        int temp;

        if(flipX){
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width/2; x++){
                    temp = input.getRGB(x0+x,y0+y);
                    input.setRGB(x0+x,y0+y,input.getRGB(x1-1-x,y0+y));
                    input.setRGB(x1-1-x,y0+y,temp);
                }
            }

        }else{
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height/2; y++){
                    temp = input.getRGB(x0+x,y0+y);
                    input.setRGB(x0+x,y0+y,input.getRGB(x0+x,y1-1-y));
                    input.setRGB(x0+x,y1-1-y,temp);
                }
            }
            
        }
        return input;
    }
}

