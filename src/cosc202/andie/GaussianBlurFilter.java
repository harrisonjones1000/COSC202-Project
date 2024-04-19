package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.text.*;
import cosc202.andie.ConvOp;
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
    private boolean negOffSet;

    /**
     * <p>
     * Construct a Guassian filter with the given size and if a negative offset wants to be added.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianFilter
     * @param negOffSet If a negative offset is to be added to the output.
     */
    public GaussianBlurFilter(int radius, boolean negOffSet) {
        this.radius = radius;
        this.negOffSet = negOffSet;   
    }
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
        this.negOffSet = false;    
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
        this.negOffSet = false;    
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
        output = ConvOp.convOp(input,kernel,radius,negOffSet);
        return output;
    }
}
