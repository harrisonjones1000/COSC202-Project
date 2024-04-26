package cosc202.andie;
import java.awt.*;
import javax.swing.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    
    /**
     * The image to display in the ImagePanel.
     */
    public EditableImage image; 

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    public Rectangle selected;

    public Draw draw;


    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     * <p>
     * If possible a default image is initially rendered when the app loads, if an exception is thrown the 
     * app just loads without a default image.
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        try{
            //Default image when the app initially loads.
            image.open("src\\assets\\Default_Image.png");

        }catch(Exception e){
            System.out.println("default image does not exists");
        }

        draw = new Draw();
    }


    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage(){
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }


    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * Will draw selection and drawings as they're being drawn.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image.hasImage()){
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);

            if(selected!=null){
                g2.drawRect(selected.x, selected.y, selected.width, selected.height);
            }
            if(draw.getRectangle()!=null&&draw.getColour()!=null&&draw.getShape()!=null&&selected==null){
                int x = (int)draw.getRectangle().getX();
                int y = (int)draw.getRectangle().getY();
                int width = (int)draw.getRectangle().getWidth();
                int height = (int)draw.getRectangle().getHeight();
                g2.setColor(draw.getColour());
                if(draw.getShape().equals("Rectangle")){
                    if(draw.getFill()) g2.fillRect(x,y,width,height);
                    else if(!draw.getFill())g2.drawRect(x,y,width,height);
                }else if(draw.getShape().equals("Oval")){
                    if(draw.getFill()) g2.fillOval(x,y,width,height);
                    else if(!draw.getFill()) g2.drawOval(x,y,width,height);
                }else if(draw.getShape().equals("Line")){
                    if(draw.getLine()) g2.drawLine(x,y,x+width,y+height);
                    else if(!draw.getLine()) g2.drawLine(x+width,y,x,y+height);
                }
            }
            g2.dispose();
        }
    }

     /**
     * <p>
     * Draw class contains datafields on what needs to be drawm and 
     * has methods to set and get them
     * </p>
     */
    public class Draw{
        private Rectangle rectangle;
        private Color colour;
        private String shape;
        private boolean fill = false;
        private boolean line;

        public Rectangle getRectangle(){
            return this.rectangle;
        }
        public Color getColour(){
            return this.colour;
        }
        public String getShape(){
            return this.shape;
        }
        public boolean getFill(){
            return this.fill;
        }
        public boolean getLine(){
            return this.line;
        }

        public void setRectangle(Rectangle rectangle){
            this.rectangle=rectangle;
        }
        public void setColour(Color colour){
            this.colour=colour;
        }
        public void setShape(String shape){
            this.shape=shape;
        }
        public void setFill(){
            this.fill=!(this.fill);
        }
        public void setLine(boolean line){
            this.line = line;
        }
    }
}
