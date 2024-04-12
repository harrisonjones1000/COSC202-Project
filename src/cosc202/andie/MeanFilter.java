package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.text.*;
/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a convolution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {
    
    /**-private
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MeanFilter() {
        this(1);
    }

    /**
     * <p>
     * Takes a Buffered Image and Kernel and apply a convolution filter from said kernal.
     * </p
     * >
     * <p>
     * Note that values outside the image instead takes values
     * </p>
     * @param input The BufferedImage
     * @param kernel The Kernel for the convolution.
     * @return The BufferedImage with the convolution filter applied
     */
    public BufferedImage convOp(BufferedImage input, Kernel kernel){
        float[] kerArray = new float[(2*radius + 1)*(2*radius + 1)];
        kernel.getKernelData(kerArray);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                ArrayList<Integer> array = new ArrayList<Integer>();
                for (int i = -radius; i < radius + 1; i++){
                    for (int j = -radius; j < radius + 1; j++){        
                        int argb;
                        try{
                            argb = input.getRGB(x+i, y+j);
                        }catch(Exception ArrayIndexOutOfBoundsException){
                            if ((x+i >= 0 && x+i < input.getWidth())&&(y+j < 0 && y+j > input.getWidth())){
                                argb = input.getRGB(x+i, y);
                            } else if ((x+i < 0 && x+i > input.getWidth())&&(y+j >= 0 && y+j > input.getWidth())){
                                argb = input.getRGB(x, y+j);
                            }else{
                                argb = input.getRGB(x, y);
                            }
                                
                        }
                        array.add(argb);
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
                float a_mean = 0;
                float r_mean = 0;
                float g_mean = 0;
                float b_mean = 0; 

                for (int i = 0; i < a_array.length; i++){
                    a_mean += a_array[i]*kerArray[i];
                    r_mean += r_array[i]*kerArray[i];
                    g_mean += g_array[i]*kerArray[i];
                    b_mean += b_array[i]*kerArray[i];
                }
                DecimalFormat fmt = new DecimalFormat("0");
                String a_m = fmt.format(a_mean);
                String r_m = fmt.format(r_mean);
                String g_m = fmt.format(g_mean);
                String b_m = fmt.format(b_mean);
                int a_me = Integer.parseInt(a_m);
                int r_me = Integer.parseInt(r_m);
                int g_me = Integer.parseInt(g_m);
                int b_me = Integer.parseInt(b_m);
                int argb = ((int)a_me << 24) | ((int)r_me << 16) | ((int)g_me << 8) | (int)b_me;
                output.setRGB(x, y, argb);
            }
        }

        return output;
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting blurred image.
     =*/

    
    public BufferedImage apply(BufferedImage input) {
        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];
        Arrays.fill(array, 1.0f/size);
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        output = convOp(input,kernel);

        return output;
    }


}
