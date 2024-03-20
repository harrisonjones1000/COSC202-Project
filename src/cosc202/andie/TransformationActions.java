package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TransformationActions{

    protected ArrayList<Action> actions;

    public TransformationActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction("Resize", null, "Resizes image", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAction("Rotate", null, "Rotates image clockwise", Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new FlipAction("Flip", null, "Flips image", Integer.valueOf(KeyEvent.VK_F)));
    }

    public JMenu createJMenu() {
        JMenu fileMenu = new JMenu("Transformations");

        fileMenu.add(new JMenuItem(actions.get(0)));
        fileMenu.add(new JMenuItem(actions.get(1)));
        fileMenu.add(new JMenuItem(actions.get(2)));

        return fileMenu;
    }

    public class ResizeAction extends ImageAction{
        
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            //Code needed to generate a dialog box to pick the scaleFactor
            double scaleFactor = 2;
            target.getImage().apply(new Resize(scaleFactor));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class RotateAction extends ImageAction{
        
        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new Rotate());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class FlipAction extends ImageAction{
        
        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new Flip());
            target.repaint();
            target.getParent().revalidate();
        }
    }
}