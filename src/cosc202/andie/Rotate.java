package cosc202.andie;

import java.awt.image.*;

public class Rotate implements ImageOperation, java.io.Serializable {
    
    private int rotate;

    Rotate(int rotate){
        this.rotate = rotate;
    }

    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        if (rotate == 0){ //90 degrees right
            BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    output.setRGB(height-1-y,x,input.getRGB(x,y));
                }
            }
            return output;
        }else if(rotate == 1){ //90 degrees left
            BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    output.setRGB(y,width-1-x,input.getRGB(x,y));
                }
            }
            return output;
        }else{
            int temp;
            for(int x = 0; x < width/2; x++){
                for(int y = 0; y < height; y++){
                    temp = input.getRGB(x,y);
                    input.setRGB(x,y,input.getRGB(width-1-x,height-1-y));
                    input.setRGB(width-1-x,height-1-y, temp);
                }
            }
            return input;
        }
    }
}
