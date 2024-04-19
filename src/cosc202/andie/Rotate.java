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
    private int width, height, x0, y0, x1, y1, d;

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
        width = input.getWidth();
        height = input.getHeight();

        /*Rotates 90 degrees left */
        if (rotate == 0){
            /*Full image rotation */
            if(rectangle==null){
                BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
                for(int x = 0; x < width; x++){
                    for(int y = 0; y < height; y++){
                        output.setRGB(height-1-y,x,input.getRGB(x,y));
                    }
                }
                return output;
            }
            /*Selected image rotation */
            else{
                rectanglefields();
                int[][] array = getSelectedRGB(x0,y0,width,height,input);
                for(int y = 0; y < height; y++){
                    for(int x = 0; x < width; x++){
                        if(width>height)input.setRGB(x0+d+height-y,y0-d+x,array[x][y]);
                        if(height>width)input.setRGB(x0+width+d-y,y0-d+width+x,array[x][y]);
                    }
                }
                System.out.println("right");
                return input;
            }   
        /*Rotates 90 degrees left */
        }else if(rotate == 1){
            /*Full image rotation */
            if(rectangle==null){
                BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
                for(int x = 0; x < width; x++){
                    for(int y = 0; y < height; y++){
                        output.setRGB(y,width-1-x,input.getRGB(x,y));
                    }
                }
                return output;
            }
            /*Selected image rotation */
            else{
                rectanglefields();
                int[][] array = getSelectedRGB(x0,y0,width,height,input);
                for(int y=0; y<height; y++){
                    for(int x=0; x<width; x++){
                        if(height>width)input.setRGB(x0-d+y,y0+d+width-x,array[x][y]);
                        if(width>height)input.setRGB(x0+d+height-y,y0+height+d-x,array[x][y]);
                    }
                }
                return input;
            }
        /*Rotates 180 degrees */
        }else{ 
            if(rectangle!=null){
                rectanglefields();
            }else{
                x0 = 0;
                y0 = 0;
                x1 = width;
                y1 = height;
            }
            for(int x=0; x<(double)width/2; x++){
                if(width%2==1 && x==width/2) height = height/2;
                for(int y=0; y<height; y++){
                    int temp = input.getRGB(x0+x,y0+y);
                    input.setRGB(x0+x,y0+y,input.getRGB(x1-1-x,y1-1-y));
                    input.setRGB(x1-1-x,y1-1-y,temp);
                }
            }
            return input;
        }
    }

    public void rectanglefields(){
        width = (int)rectangle.getWidth();
        height = (int)rectangle.getHeight();
        x0 = (int)rectangle.getX();
        y0 = (int)rectangle.getY();
        x1 = x0 + width;
        y1 = y0 + height;
        d = Math.abs((width-height)/2);
    }

    public int[][] getSelectedRGB(int x0, int y0, int width, int height, BufferedImage input){
        int[][] b = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                b[x][y] = input.getRGB(x0+x,y0+y);
                input.setRGB(x0+x,y0+y,0);
            }
        }
        return b;
    }
}