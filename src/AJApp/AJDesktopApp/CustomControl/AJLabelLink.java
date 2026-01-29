package AJApp.AJDesktopApp.CustomControl;

import javax.swing.ImageIcon;

import AJInfrastructura.AJAppStyle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AJLabelLink extends AJLabel implements MouseListener {

    AJLabelLink(String text) {
        super(text);
        setPersonalizacion();
    }

    AJLabelLink(String text, String iconPath) {
        super(text);
        setPersonalizacion();
        setIcon(new ImageIcon(iconPath));
    }

    void setPersonalizacion() {
        addMouseListener(this);
        setOpaque(false);
        setForeground(AJAppStyle.COLOR_FONT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // code
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // code
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // code
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(AJAppStyle.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(AJAppStyle.CURSOR_DEFAULT);
    }
}
