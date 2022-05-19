import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GButton extends GCompound implements MouseListener {
    private double width, height;
    private GRect rect;
    private GLabel label;
    private int buttonValue;



    @Override
    public void mouseClicked(MouseEvent e) {
        ButtonManager.buttonClicked(buttonValue);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
