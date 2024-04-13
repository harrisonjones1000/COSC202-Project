package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to rotate an image a specified way.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Harrison Jones
 * @version 1.0
 */
public class Rotate implements ImageOperation, java.io.Serializable {

    /**Controls the type of rotation operation
     * rotate = 0, 90 degrees right
     * rotate = 1, 90 degrees left
     * rotate = anything else, 180 degrees
    */
    private int rotate;
    private Rectangle rectangle;

    /**Constructs a Rotate operation with a given direction
     * <p>
     * This class supports left and right rotations of 90 degrees, and 180 degrees.
     * 
     * @param rotate Indicates the type of rotation
     */
    Rotate(int rotate, Rectangle rectangle){
        this.rotate = rotate;
        this.rectangle=rectangle;
    }

    /**
     * <p>
     * Apply a Rotate operation to an image.
     * </p>
     * 
     * <p>
        The Rotate operation is controlled by an if statement in code, controlled by {@link rotate}.

     * </p>
     * 
     * @param input The image to apply the Rotate to.
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();
        
        if (rotate == 0){ //90 degrees right
            BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    output.setRGB(height-1-y,x,input.getRGB(x,y));
                }
            }
            return output;
        }else if(rotate == 1){ //90 degrees left
            BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    output.setRGB(y,width-1-x,input.getRGB(x,y));
                }
            }
            return output;
        }else{ //180 degrees
            int x0,y0,x1,y1;
            if(rectangle==null){
                x0=0;
                y0=0;
                x1=width;
                y1=height;
                int temp;
                for(int x = x0; x < width/2; x++){
                    for(int y = y0; y < height; y++){
                        temp = input.getRGB(x,y);
                        input.setRGB(x,y,input.getRGB(width-1-x,height-1-y));
                        input.setRGB(width-1-x,height-1-y, temp);
                    }
                }
                return input;
            }else{
                x0=(int)rectangle.getX();
                y0=(int)rectangle.getY();
                x1=(int)rectangle.getWidth()+x0;
                y1=(int)rectangle.getHeight()+y0;
                int temp;
                for(int x = x0; x < width/2; x++){
                    for(int y = y0; y < height; y++){
                        temp = input.getRGB(x,y);
                        input.setRGB(x,y,input.getRGB(width-1-x,height-1-y));
                        input.setRGB(width-1-x,height-1-y, temp);
                    }
                }
                return input;
            }
            
        }
    }
}
