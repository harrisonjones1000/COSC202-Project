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
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        int size = (2*radius+1) * (2*radius+1); 
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int counter = 0;
                int [] a_array = new int [size];
                int [] r_array = new int [size];
                int [] g_array = new int [size];
                int [] b_array = new int [size];

                for (int i = -radius; i < radius + 1; i++){
                    for (int j = -radius; j < radius + 1; j++){
                        int argb = input.getRGB(x, y);
                        int a = (argb & 0xFF000000) >> 24;
                        int r = (argb & 0x00FF0000) >> 16;
                        int g = (argb & 0x0000FF00) >> 8;
                        int b = (argb & 0x000000FF);
                        a_array[counter] = a;
                        r_array[counter] = r;
                        g_array[counter] = g;
                        b_array[counter] = b;
                        counter++;
                    }

                }
                Arrays.sort(a_array);
                Arrays.sort(r_array);
                Arrays.sort(g_array);
                Arrays.sort(b_array);
                System.out.println(Arrays.toString(r_array));
                int a = a_array[radius + 1];
                int r = r_array[radius + 1];
                int g = g_array[radius + 1];
                int b = b_array[radius + 1];

                int argb = (a << 24) | (r << 16) | (g << 8) | b;
                
                output.setRGB(x, y, argb);

            }
        }

        return output;
    }
}