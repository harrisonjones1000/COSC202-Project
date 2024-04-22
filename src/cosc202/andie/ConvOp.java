package cosc202.andie;
import java.awt.image.*;
import java.util.*;
import java.text.*;
/**
 * <p>
 * A custom convolution impementantion
 * </p>
 * 
 * <p>
 * A convolution applys a filter on an image by using a kernel of pixels in a radius.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ryan Wilks, Steven Mills, who made Convert To Grey, which this is based off of.
 * @version 1.0
 */
public class ConvOp {
    /**
     * <p>
     * Takes a Buffered Image and Kernel and apply a convolution filter from said kernal.
     * </p
     * >
     * <p>
     * Note that values outside the image instead takes values from the nearest valid value.
     * This is what the Try-catch statement is for.
     * 
     * Also Note that the Negative adjustment does not apply to alpha value of the image.
     * </p>
     * @param input The BufferedImage
     * @param kernel The Kernel for the convolution.
     * @param radius The radius of the kernel.
     * @param negOffSet Allows for negative values to have meaning in the convolution.
     * @return The BufferedImage with the convolution filter applied
     */
    public static BufferedImage convOp(BufferedImage input, Kernel kernel, int radius, boolean negOffSet){
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
                float[] a_array = new float[array.size()];
                float[] r_array = new float[array.size()];
                float[] g_array = new float[array.size()];
                float[] b_array = new float[array.size()];
                for (int i = 0; i < array.size(); i++){
                    a_array[i] = (array.get(i) & 0xFF000000) >> 24;
                    if (a_array[i] == -1){
                        a_array[i] = 255;
                    }
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
                if (negOffSet){
                    aRounded += 127;
                    rRounded += 127;
                    gRounded += 127;
                    bRounded += 127;
                }
                if (aRounded > 255) aRounded = 255;
                if (rRounded > 255) rRounded = 255;
                if (gRounded > 255) gRounded = 255;
                if (bRounded > 255) bRounded = 255;
                if (aRounded < 0) aRounded = 0;
                if (rRounded < 0) rRounded = 0;
                if (gRounded < 0) gRounded = 0;
                if (bRounded < 0) bRounded = 0;
                int argb = (aRounded << 24) | (rRounded << 16) | (gRounded<< 8) | bRounded;
                output.setRGB(x, y, argb);
            }
        }

        return output;
    }
}
