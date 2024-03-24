package cosc202.andie;

import java.awt.image.*;

public class Flip implements ImageOperation, java.io.Serializable {
    
    private boolean flipX;

    Flip(boolean flipX){
        this.flipX = flipX;
    }

    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();
        
        int temp;

        if(flipX){
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height/2; y++){
                    temp = input.getRGB(x,y);
                    input.setRGB(x,y,input.getRGB(x,height-1-y));
                    input.setRGB(x,height-1-y,temp);
                }
            }
        }else{
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width/2; x++){
                    temp = input.getRGB(x,y);
                    input.setRGB(x,y,input.getRGB(width-1-x,y));
                    input.setRGB(width-1-x,y,temp);
                }
            }
            
        }

        

        return input;
    }
}

