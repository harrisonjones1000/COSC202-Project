import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DecimalFormat;

import cosc202.andie.GaussianBlurFilter;

public class GaussianBlurFilterTests {
    
    @Test
    public void testGaussianFunction(){
        GaussianBlurFilter filter = new GaussianBlurFilter(1);
        float num = filter.GaussianFunction(0, 0, (float)1.0/(float)3.0);
        DecimalFormat df = new DecimalFormat("0.000");
        String adjustedNum = df.format(num);
        assertEquals("1.432",adjustedNum);
    }
    @Test
    public void testArray(){
        int radius = 1;
        GaussianBlurFilter filter = new GaussianBlurFilter(radius);
        float[] array = filter.kernalArrayMaker(1);
        DecimalFormat df = new DecimalFormat("0.000");
        String[] arrayadjusted = new String[9];
        for (int i = 0; i < (2*radius + 1)*(2*radius + 1); i++){
            arrayadjusted[i] = df.format(array[i]);
        }
        String[] arraytrue = {"0.000", "0.011", "0.000",
                              "0.011", "0.957", "0.011",
                              "0.000", "0.011", "0.000"};
        for (int i = 0; i < (2*radius + 1)*(2*radius + 1); i++){
            assertEquals(arraytrue[0],arrayadjusted[0]);
        }

    }
}
