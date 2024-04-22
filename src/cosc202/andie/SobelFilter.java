package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.text.*;
import cosc202.andie.ConvOp;

/**
 * <p>
 * ImageOperation to apply an Sobel filter.
 * </p>
 * 
 * <p>
 * A Sobel filter enhances the differences between two values of opposite pixels and half of the pixels adjacent.
 * There are 2 implemented ways this is done.
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

public class SobelFilter implements ImageOperation, java.io.Serializable{
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     * Currently, the only possible value is 1, this is just here incase more radius values are to be implemented.
     */
    private int radius;
    private boolean negOffSet;
    private int arrayChoice;

     /**
     * <p>
     * Construct a Sobel filter with the size of 3 x 3 and if a negative offset wants to be added.
     * </p
     * @param negOffSet If a negative offset is to be added to the output.
     */
    SobelFilter(boolean negOffSet, int arrayChoice) {
        this.radius = 1;
        this.negOffSet = negOffSet;
        this.arrayChoice = arrayChoice;
    }
    
    /**
     * <p>
     * Construct a Sobel filter with the size of 3 x 3.
     * </p
     */
    SobelFilter() {
        this.radius = 1;
        this.negOffSet = false;
        this.arrayChoice = 0;
    }

    /**
     * <p>
     * Apply a Sobel filter to an image.
     * </p>
     * 
     * <p>
     * The Sobel filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger sharpening.
     * </p>
     * 
     * @param input The image to apply the Sharpening filter to.
     * @return The resulting (sharpened)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float [][] array = {{-1/2f, 0, 1/2f,
                            -1f, 0, 1f,
                            -1/2f, 0, 1/2f},
                            {-1/2f, -1f, -1/2f,
                            0, 0, 0,
                            1/2f, 1f, 1/2f},
                            };
        System.out.println(Arrays.toString(array[arrayChoice]));
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array[arrayChoice]);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        output = ConvOp.convOp(input, kernel, radius, negOffSet);

        return output;
    }
}
