package cosc202.andie;

import java.awt.image.*;

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

    /**Constructs a Flip operation with a given direction
     * <p>
     * This class supports image flipping in the horizontal and vertical planes. 
     * The direction of this operation depends on the given boolean.
     * 
     * @param flipX Indicates the direction of the flip operation.
     */
    Flip(boolean flipX){
        this.flipX = flipX;
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
        int width = input.getWidth();
        int height = input.getHeight();
        
        int temp;

        if(flipX){
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width/2; x++){
                    temp = input.getRGB(x,y);
                    input.setRGB(x,y,input.getRGB(width-1-x,y));
                    input.setRGB(width-1-x,y,temp);
                }
            }

        }else{
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height/2; y++){
                    temp = input.getRGB(x,y);
                    input.setRGB(x,y,input.getRGB(x,height-1-y));
                    input.setRGB(x,height-1-y,temp);
                }
            }
            
        }
        return input;
    }
}

