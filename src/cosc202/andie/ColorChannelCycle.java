package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the red, green and blue color channels of the image.
 * </p>
 * 
 * <p>
 * By its nature color cycling three times in any direction will result in the
 * original image again.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Calan McDermott, Steven Mills who made ConvertToGrey which this is
 *         based off
 * @version 1.0
 */
public class ColorChannelCycle implements ImageOperation, java.io.Serializable {
    /**
     * Boolean that controls direction of color channel swapping. True indicates that red moves to green,
     * green moves to blue and blue moves to red, while false indicates the opposite.
     */
    private boolean rTogTob = false;
    /**
     * <p>
     * Create a new ColorChannelCycle operation with the given direction.
     * @param rTogTob Boolean indicating direction of color channel swapping.
     * </p>
     */
    ColorChannelCycle(boolean rTogTob) {
        this.rTogTob = rTogTob;
    }

    /**
     * <p>
     * Apply color channel cycling conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion consists of setting each color value to its neighbhor, direction indicated by the 
     * boolean {@link rTogTob}.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting inverted image.
     */
    public BufferedImage apply(BufferedImage input) {
        if (rTogTob) {
            for (int y = 0; y < input.getHeight(); ++y) {
                for (int x = 0; x < input.getWidth(); ++x) {
                    int argb = input.getRGB(x, y);
                    int a = (argb & 0xFF000000) >> 24;
                    int r = (argb & 0x00FF0000) >> 16;
                    int g = (argb & 0x0000FF00) >> 8;
                    int b = (argb & 0x000000FF);
                    // Normal image is a,r,g,b
                    argb = (a << 24) | (g << 16) | (b << 8) | r;
                    input.setRGB(x, y, argb);
                }
            }
        } else {
            for (int y = 0; y < input.getHeight(); ++y) {
                for (int x = 0; x < input.getWidth(); ++x) {
                    int argb = input.getRGB(x, y);
                    int a = (argb & 0xFF000000) >> 24;
                    int r = (argb & 0x00FF0000) >> 16;
                    int g = (argb & 0x0000FF00) >> 8;
                    int b = (argb & 0x000000FF);
                    // Normal image is a,r,g,b
                    argb = (a << 24) | (b << 16) | (r << 8) | g;
                    input.setRGB(x, y, argb);
                }
            }
        }
        return input;
    }
}
