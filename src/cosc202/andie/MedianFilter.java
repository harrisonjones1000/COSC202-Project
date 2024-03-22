package cosc202.andie;
import java.awt.image.*;
import java.util.*;
/**
 * <p>
 * ImageOperation to apply a Median Blur filter.
 * </p>
 * 
 * <p>
 * A Median Blur filer blurs an image by finding the median of pixels in a radius.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ryan Wilks, Steven Mills, who made Mean Filter, which this is based off of.
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable{
     /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth,
     * this is also used to find a value of the filter (know as sigma)
     */
    private int radius;
    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the array used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    public MedianFilter(int radius) {
        this.radius = radius;    
    }
    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    MedianFilter() {
        this(1);    
    }
    
    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * Unlike many filters, the Median filter is not implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int size = (2*radius+1) * (2*radius+1); 
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int[][] array = new int[4][size];
                int counter = 0;
                for (int i = y - radius; i < y + radius + 1; i++){
                    for (int j = x - radius; j < x + radius + 1; j++){
                        int argb = input.getRGB(x, y);
                        array[0][counter] = (argb & 0xFF000000) >> 24;
                        array[1][counter] = (argb & 0x00FF0000) >> 16;
                        array[2][counter] = (argb & 0x0000FF00) >> 8;
                        array[3][counter] = (argb & 0x000000FF);
                    }
                }
                int[] arraya = array[0];
                int[] arrayr = array[1];
                int[] arrayg = array[2];
                int[] arrayb = array[3];
                Arrays.sort(arraya);
                Arrays.sort(arrayr);
                Arrays.sort(arrayg);
                Arrays.sort(arrayb);
                int argb = arraya[radius + 1] | arrayr[radius + 1] | arrayg[radius + 1] | arrayb[radius + 1];
                input.setRGB(x,y,argb);
            }
        }

        return input;
    }
}
