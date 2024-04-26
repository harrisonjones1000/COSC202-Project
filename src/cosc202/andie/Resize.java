package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Rectangle;

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
    private Rectangle selected;
    
    /**
     * Constructs a Resize operation with the given {@link scaleFactor}.
     * 
     * @param scaleFactor The scaleFactor of the newly constructed Resize.
     */
    Resize(double scaleFactor, Rectangle selected) {
        this.scaleFactor = scaleFactor;   
        this.selected = selected; 
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
        if(selected==null){
            int newWidth = (int)(scaleFactor*input.getWidth());
            int newHeight = (int)(scaleFactor*input.getHeight());

            Image img = input.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH);

            BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = output.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            
            return output;
        }else{
            int rWidth = selected.width;
            int rHeight = selected.height;
            int x0 = (int)selected.getX();
            int y0 = (int)selected.getY();

            BufferedImage rectangle = new BufferedImage(rWidth, rHeight, BufferedImage.TYPE_INT_ARGB);
            for(int x = 0; x < rWidth; x++){
                for(int y = 0; y < rHeight; y++){
                    rectangle.setRGB(x,y,input.getRGB(x0+x, y0+y));
                    input.setRGB(x0+x, y0+y,0);
                }
            }

            Image scaledImg = rectangle.getScaledInstance((int)(rWidth*scaleFactor), (int)(rHeight*scaleFactor),Image.SCALE_SMOOTH);
            BufferedImage scaledBImg = new BufferedImage(scaledImg.getWidth(null), scaledImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = scaledBImg.createGraphics();
            g.drawImage(scaledImg, 0, 0, null);
            g.dispose();

            for(int x = 0; x < scaledImg.getWidth(null); x++){
                for(int y = 0; y < scaledImg.getHeight(null); y++){
                    if(x0+x<input.getWidth()&&y0+y<input.getHeight()) input.setRGB(x0+x, y0+y, scaledBImg.getRGB(x,y));
                }
            }
            return input;
        }
        
        
    }

}
