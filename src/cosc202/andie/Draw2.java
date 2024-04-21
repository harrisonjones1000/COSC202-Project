package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.awt.Rectangle;
import java.awt.Color;

public class Draw2 implements ImageOperation, java.io.Serializable {
    private Rectangle rectangle;
    private Color colour;
    private String shape;
    private boolean fill = false;
    private boolean line;
    
    Draw2(Rectangle rectangle, Color colour, String shape, boolean fill, boolean line){
        this.rectangle = rectangle;
        this.colour = colour;
        this.shape=shape;
        this.fill=fill;
        this.line = line;
    }
    
    public BufferedImage apply(BufferedImage input){
        Graphics2D g2 = input.createGraphics();
        if(rectangle!=null&&colour!=null&&shape!=null){
            int x = (int)rectangle.getX();
            int y = (int)rectangle.getY();
            int width = (int)rectangle.getWidth();
            int height = (int)rectangle.getHeight();
            g2.setColor(colour);
            if(shape.equals("Rectangle")){
                if(fill) g2.fillRect(x,y,width,height);
                else if(!fill) g2.drawRect(x,y,width,height);
            }else if(shape.equals("Oval")){
                if(fill) g2.fillOval(x,y,width,height);
                else if(!fill) g2.drawOval(x,y,width,height);
            }else if(shape.equals("Line")){
                if(line) g2.drawLine(x,y,x+width,y+height);
                else if(!line) g2.drawLine(x+width,y,x,y+height);
            }
        }
        return input;
    }
}
