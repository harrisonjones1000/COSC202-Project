package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.swing.event.MouseInputAdapter;
import java.awt.Rectangle;

public class DrawActions {

    private boolean drawing;
    private boolean fill;
    private String Colour;
    private String Shape;

    private DrawingArea d;

    public DrawActions(){
        this.drawing = false;
        this.fill = false;
    }

    public JMenu createJMenu(){
        JMenu colours = new JMenu("Colours");
        ButtonGroup c = new ButtonGroup();
        JRadioButtonMenuItem red = new JRadioButtonMenuItem(new DrawColourAction("red", null, "red", null, "red"));
        JRadioButtonMenuItem green = new JRadioButtonMenuItem(new DrawColourAction("green", null, "green", null, "green"));
        c.add(red);
        c.add(green);
        colours.add(red);
        colours.add(green);

        JMenu shapes = new JMenu("Shapes");
        ButtonGroup s = new ButtonGroup();
        JRadioButtonMenuItem rect = new JRadioButtonMenuItem(new DrawShapeAction("Rectangle", null, "Rectangle", null, "Rectangle"));
        JRadioButtonMenuItem oval = new JRadioButtonMenuItem(new DrawShapeAction("Oval", null, "Oval", null, "Oval"));
        JRadioButtonMenuItem line = new JRadioButtonMenuItem(new DrawShapeAction("Line", null, "Line", null, "Line"));
        s.add(rect);
        s.add(oval);
        s.add(line);
        shapes.add(rect);
        shapes.add(oval);
        shapes.add(line);
        JCheckBoxMenuItem fill = new JCheckBoxMenuItem(new DrawFillAction("Fill",null,"Fill",null));
        shapes.add(fill);

        JMenu drawMenu = new JMenu("Draw");
        drawMenu.add(new JCheckBoxMenuItem(new DrawAction("Draw On",null,"Draw On",null)));
        drawMenu.add(colours);
        drawMenu.add(shapes);

        return drawMenu;
    }

    /**
     * <p>
     * Action when click Draw checkbox
     * </p>
     */
    public class DrawAction extends ImageAction{
        /**
         * <p>
         * Create a new DrawAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Switches the drawing datafield on/off
         * </p>
         */
        public void actionPerformed(ActionEvent e){
            drawing = !drawing;
            if(drawing){
                d = new DrawingArea(target);
            }
            
            System.out.println(drawing);
        }
    }

    /**
     * <p>
     * Action when click a Colour option
     * </p>
     */
    public class DrawColourAction extends ImageAction{
        private String colour;
        /**
         * <p>
         * Create a new DrawColourAction of a specified colour.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param colour what colour will be drawn in
         */
        DrawColourAction(String name, ImageIcon icon, String desc, Integer mnemonic, String colour){
            super(name, icon, desc, mnemonic);
            this.colour = colour;
        }

        /**
         * <p>
         * Action when click a Colour option, sets the Colour datafield
         * </p>
         */
        public void actionPerformed(ActionEvent e){
            System.out.println(Colour);
            Colour = colour;
        }
    }

    /**
     * <p>
     * Action when click a Shape option
     * </p>
     */
    public class DrawShapeAction extends ImageAction{
        private String shape;
        /**
         * <p>
         * Create a new DrawShapeAction of a specified shape.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param Shape what shape to be drawn
         */
        DrawShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, String Shape){
            super(name, icon, desc, mnemonic);
            this.shape = Shape;
        }

        /**
         * <p>
         * Sets the Colour datafield
         * </p>
         */
        public void actionPerformed(ActionEvent e){
            Shape = shape;
            System.out.println(Shape);
        }
    }

    /**
     * <p>
     * Action when click Fill checkbox
     * </p>
     */
    public class DrawFillAction extends ImageAction{
        /**
         * <p>
         * Create a new DrawFillAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawFillAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Switches the fill datafield on/off
         * </p>
         */
        public void actionPerformed(ActionEvent e){
            fill = !fill;
            System.out.println(fill);
        }
    }

    private class DrawingArea extends ImagePanel{
        private ImagePanel panel;
        private boolean hasImage;

        DrawingArea(ImagePanel panel){
            this.panel=panel;
            hasImage = panel.image.hasImage();

            MyListener myListener = new MyListener();
            panel.addMouseListener(myListener);
            panel.addMouseMotionListener(myListener);
        }
        
        private class MyListener extends MouseInputAdapter{
            private int x0;
            private int y0;
            private int width = panel.image.getCurrentImage().getWidth();
            private int height = panel.image.getCurrentImage().getHeight();

            public void mousePressed(MouseEvent e){
                if(drawing&&hasImage){
                    x0 = e.getX();
                    y0 = e.getY();
                }
            }

            public void mouseDragged(MouseEvent e) {
                if(drawing&&hasImage){
                    Rectangle r;
                    int x1 = e.getX();
                    int y1 = e.getY();

                    if(x1>width) x1=width;
                    if(y1>height) y1=height;
                    if(x1<0) x1=0;
                    if(y1<0) y1=0;
                    
                    if(x0<x1&&y0<y1){
                        r = new Rectangle(x0,y0,x1-x0,y1-y0);
                    }else if(x0<x1){
                        r = new Rectangle(x0,y1,x1-x0,y0-y1);
                    }else if(x0>=x1&&y0>=y1){
                        r = new Rectangle(x1,y1,x0-x1,y0-y1);
                    }else{
                        r = new Rectangle(x1,y0,x0-x1,y1-y0);
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                if(drawing&&hasImage){
                    panel.repaint();
                    panel.getParent().revalidate();        
                }
            }
        }
    }
}
