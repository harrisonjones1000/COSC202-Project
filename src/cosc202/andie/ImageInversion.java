package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to convert an image from colour to its color inversion.
 * </p>
 * 
 * <p>
 * The images produced by this operation are identical to the original image except the color values
 * of each pixel are subtracted from 255.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Calan McDermott, Steven Mills who made ConvertToGrey which this is based off
 * @version 1.0
 */
public class ImageInversion implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new ImageInversion operation.
     * </p>
     */
    ImageInversion() {

    }

    /**
     * <p>
     * Apply inversion conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion from red, green, and blue values to their inverses consists of subtracting
     * each value from 255, representing white.
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

                int invr = 255-r;
                int invg = 255-g;
                int invb = 255-b;

                argb = (a << 24) | (invr << 16) | (invg << 8) | invb;
                input.setRGB(x, y, argb);

            }
            
        }
        
        return input;
    }
}