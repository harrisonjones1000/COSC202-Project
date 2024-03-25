package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * A Sharpen filter sharpens an image by enhancing the differences between neighbouring values of pixels.
 * This can be implemented by a convolution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Ryan Wilks, Steven Mills, who made Mean Filter, which this is based off of.
 * @version 1.0
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable{
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     * Currently, the only possible value is 1, this is just here incase more radius values are to be implemented.
     */
    private int radius;

    /**
     * <p>
     * Construct a Sharpen filter with the size of 3 x 3.
     * </p
     */
    SharpenFilter() {
        this.radius = 1;
    }
    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * The Sharpen filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger sharpening.
     * </p>
     * 
     * @param input The image to apply the Sharpening filter to.
     * @return The resulting (sharpened)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float [] array = { 0 , -1/2.0f, 0 ,
                        -1/2.0f, 3.0f, -1/2.0f,
                        0 , -1/2.0f, 0 };

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
}
