package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the red, green and blue color channels of the image.
 * </p>
 * 
 * <p>
 *  By its nature color cycling three times in any direction will result in the original image again.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Calan McDermott, Steven Mills who made ConvertToGrey which this is based off
 * @version 1.0
 */
public class ColorChannelCycle implements ImageOperation, java.io.Serializable {
    /**
     * <p>
     * Create a new ColorChannelCycle operation.
     * </p>
     */
    private boolean rTogTob = false;
    ColorChannelCycle(boolean rTogTob) {
        this.rTogTob = rTogTob;
    }

    /**
     * <p>
     * Apply color channel cycling conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion consists of setting each variable .
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting inverted image.
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);
                //Normal image is a,r,g,b
                argb = (a << 24) | (b << 16) | (r << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
}
