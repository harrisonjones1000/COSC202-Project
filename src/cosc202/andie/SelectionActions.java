package cosc202.andie;

import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

/**
 * <p>
 * Selection added to menu, allows subsections of image to have filters applied
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Harrison Jones
 * @version 1.0
 */
public class SelectionActions{

    private ResourceBundle lan = Andie.lan;
    private boolean listening;
    private SelectionArea s;

    public SelectionActions(){
        this.listening = false;
    }

    /**
     * <p>
     * Create a checkbox element for select
     * </p>
     * 
     * @return The Select menu UI element.
     */
    public JCheckBox creatJCheckBox() {
        JCheckBox selectBox = new JCheckBox(new SelectAction("Select", null, "Select_desc", Integer.valueOf(KeyEvent.VK_R)));
        return selectBox;
    }

    /**
     * <p>
     * Action when click select checkbox
     * </p>
     */
    public class SelectAction extends ImageAction{
        /**
         * <p>
         * Create a new SelectAction.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SelectAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        /**
         * <p>
         * Callback for when the SelectAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SelectAction is triggered.
         * Switches the listening datafield on and off and generates a SelectionArea if not already initialised
         * 
         * BUG TO FIX: opening new file does not generate a new SelectionArea for the new image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            listening = !listening;
            if(listening&&s==null){
                s = new SelectionArea(target);
            }else if(!listening){
                target.selected=null;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * 
     * </p>
     */
    private class SelectionArea extends ImagePanel{
        private ImagePanel panel;
        private boolean hasImage;

        SelectionArea(ImagePanel panel){
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
                if(listening&&hasImage){
                    x0 = e.getX();
                    y0 = e.getY();
                    panel.selected = null;
                    panel.repaint();
                    panel.getParent().revalidate();
                }
            }

            public void mouseDragged(MouseEvent e) {
                if(listening&&hasImage){
                    int x1 = e.getX();
                    int y1 = e.getY();

                    if(x1>width) x1=width;
                    if(y1>height) y1=height;
                    if(x1<0) x1=0;
                    if(y1<0) y1=0;
                    
                    if(x0<x1&&y0<y1){
                        panel.selected = new Rectangle(x0,y0,x1-x0,y1-y0);
                    }else if(x0<x1){
                        panel.selected = new Rectangle(x0,y1,x1-x0,y0-y1);
                    }else if(x0>=x1&&y0>=y1){
                        panel.selected = new Rectangle(x1,y1,x0-x1,y0-y1);
                    }else{
                        panel.selected = new Rectangle(x1,y0,x0-x1,y1-y0);
                    }
                    panel.repaint();
                    panel.getParent().revalidate();
                }
            }

            public void mouseReleased(MouseEvent e) {
                if(listening&&hasImage){
                    panel.repaint();
                    panel.getParent().revalidate();        
                }
            }
        }
    }
}