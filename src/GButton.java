import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GButton extends GCompound implements MouseListener {
    private double width, height;
    private GRect rect;
    private GLabel label;
    private String inscription;
    private int buttonValue;
    private ButtonManager buttonManager;

    /**
     * @param width
     * @param height
     * @param buttonValue Value to identify button functionality in ButtonManager when clicked
     * @param inscription Text on the button
     * @param buttonManager Button manager to be called on button activation
     */
    public GButton(double width, double height, int buttonValue, String inscription, ButtonManager buttonManager) {
        this.width = width;
        this.height = height;
        this.buttonValue = buttonValue;
        this.inscription = inscription;
        this.buttonManager = buttonManager;
        rect = new GRect(width, height);
        rect.setFilled(true);
        rect.setFillColor(Color.gray);
        label = new GLabel(inscription);
        add(rect, 0, 0);
        add(label, width/2-label.getWidth()/2, height/2);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        buttonManager.buttonClicked(buttonValue);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        rect.setFillColor(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        rect.setFillColor(Color.gray);
    }
}
