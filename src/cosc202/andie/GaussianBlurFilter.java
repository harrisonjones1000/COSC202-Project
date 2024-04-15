package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.text.*;
/**
 * <p>
 * ImageOperation to apply a Gaussian Blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian Blur filer blurs an image by using a 2-dimensional Gaussian Function, normalized.
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

public class GaussianBlurFilter implements ImageOperation, java.io.Serializable{
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth,
     * this is also used to find a value of the filter (know as sigma)
     */
    private int radius;

    /**
     * <p>
     * Construct a Guassian filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianFilter
     */
    public GaussianBlurFilter(int radius) {
        this.radius = radius;    
    }
    /**
     * <p>
     * Construct a Gaussian filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian filter has radius 1.
     * </p>
     * 
     * @see GaussianBlurFilter(int)
     */
    GaussianBlurFilter() {
        this(1);    
    }
    /**<p>
    * Gets radius of the Gaussian Filter.
    * </p>
    * @return The radius.
    */
    public int getRadius(){
        return radius;
    }

    /**
     * <p>
     * Takes input values and applys them to the Gaussian Function.
     * </p>
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sigma The value of standard deviation, depends on radius.
     * @return The value at coordinates x,y with standard deviation sigma.
     */

    public float GaussianFunction(int x, int y, float sigma){
        double l = 1.0/(2*Math.PI*sigma*sigma);
        double r = Math.exp(-((x*x)+(y*y))/(2*sigma*sigma));
        return (float)(l * r);
    }

    /**
     * <p>
     * Takes a Buffered Image and Kernel and apply a convolution filter from said kernal.
     * </p
     * >
     * <p>
     * Note that values outside the image instead takes values from the nearest valid value.
     * This is what the Try-catch statement is for.
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
                float a_sum = 0;
                float r_sum = 0;
                float g_sum = 0;
                float b_sum = 0; 

                for (int i = 0; i < a_array.length; i++){
                    a_sum += a_array[i]*kerArray[i];
                    r_sum += r_array[i]*kerArray[i];
                    g_sum += g_array[i]*kerArray[i];
                    b_sum += b_array[i]*kerArray[i];
                }
                DecimalFormat fmt = new DecimalFormat("0");
                String aString = fmt.format(a_sum);
                String rString = fmt.format(r_sum);
                String gString = fmt.format(g_sum);
                String bString = fmt.format(b_sum);
                int aRounded = Integer.parseInt(aString);
                int rRounded = Integer.parseInt(rString);
                int gRounded = Integer.parseInt(gString);
                int bRounded = Integer.parseInt(bString);
                int argb = ((int)aRounded << 24) | ((int)rRounded << 16) | ((int)gRounded<< 8) | (int)bRounded;
                output.setRGB(x, y, argb);
            }
        }

        return output;
    }

    /**
     * <p>
     * Takes input of radius and calculates an array that plots gaussian function with sigma = radius/3.
     * </p>
     * 
     * <p>
     * This makes the array for the kernal to apply the Gaussian Blur Filter.
     * </p>
     * 
     * @param r the radius.
     * @return The array that plots the Gaussian Formula with sigma = r/3 and the middle of the array being at (0,0).
     */

    public float[] kernalArrayMaker(int r){
        
        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];
        float sigma = (float)radius/(float)3;
        float sum = 0;
        int counter = 0;
        for (int i = 0; i < 2*radius+1; i++){
           for (int j = 0; j < 2*radius+1; j++){
              float num = GaussianFunction(i-radius,j-radius,sigma);
              sum += num;
              array[counter] = num;
              counter++;
           }
        }
        for (int i = 0; i < size; i++){
           array[i] = array[i] / sum;
        }
        return array;
    }      

    /**
     * <p>
     * Apply a Gaussian filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian filter to.
     * @return The resulting blurred image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = kernalArrayMaker(radius);

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        output = convOp(input,kernel);
        return output;
    }
}
