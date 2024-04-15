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
 * @author Ryan Wilks, Steven Mills, who made Mean Filter and Convert To Grey, which this is based off of.
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable{
     /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth,
     * this is also used to find a value of the filter (know as sigma)
     */
    private int radius;
    private boolean negOffSet;
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
        this.negOffSet = false;    
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
        this.negOffSet = false;    
    }
    
    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * Unlike many filters, the Median filter is not implemented via convolution.
     * The method of dealing with attempting to take pixel values from outside of input image is also different.
     * Instead of taking the closest valid pixel, here the pixel just ignores it, this allows for a smoother edge compared to the method in other filters.
     * This is because the filter does not require a kernel, thus it is easy to find the median value by sorting the RGB values.
     * This is what the Try-catch statement is for.
     * The size of the array of local pixels is specified by the {@link radius} minus the valid pixels.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting blurred image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                ArrayList<Integer> array = new ArrayList<Integer>();
                for (int i = -radius; i < radius + 1; i++){
                    for (int j = -radius; j < radius + 1; j++){
                        int argb;
                        try{
                            argb = input.getRGB(x+i, y+j);
                            array.add(argb);
                        }catch(Exception ArrayIndexOutOfBoundsException){

                        }
                    }

                }
                int[] a_array = new int[array.size()];
                int[] r_array = new int[array.size()];
                int[] g_array = new int[array.size()];
                int[] b_array = new int[array.size()];
                for (int i = 0; i < array.size(); i++){
                a_array[i] = (array.get(i) & 0xFF000000) >> 24;
                r_array[i] = (array.get(i) & 0x00FF0000) >> 16;
                g_array[i] = (array.get(i) & 0x0000FF00) >> 8;
                b_array[i] = (array.get(i) & 0x000000FF);
                }
                Arrays.sort(a_array);
                Arrays.sort(r_array);
                Arrays.sort(g_array);
                Arrays.sort(b_array);
                int medianPos;
                if (array.size() % 2 == 0){
                    medianPos = array.size() / 2;
                } else{
                    medianPos = 1 + array.size() / 2;
                }
                //note, in cases with even array size, the median is not the mean of the two middle values, only the left.
                //this is uncommon as if all inputs are not out of bounds, the size of the array is (2 * radius + 1)^2,
                //since an odd number times an odd number is always odd, even size is uncommon and only tends to appear near the edges.
                int argb = (a_array[medianPos] << 24) | (r_array[medianPos] << 16) | (g_array[medianPos] << 8) | b_array[medianPos];
                
                output.setRGB(x, y, argb);

            }
        }

        return output;
    }
}
