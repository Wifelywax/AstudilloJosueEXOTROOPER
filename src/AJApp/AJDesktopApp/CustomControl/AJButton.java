package AJApp.AJDesktopApp.CustomControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import AJInfrastructura.AJAppStyle;

import javax.swing.ImageIcon;

public class AJButton extends JButton implements MouseListener {
    public AJButton(String text) {
        customizeComponent(text);
    }

    public AJButton(String text, String iconPath) {
        customizeComponent(text, iconPath);
    }

    public void customizeComponent(String text, String iconPath) {
        setSize(20, 70);
        addMouseListener(this);
        customizeComponent(text);
        setBounds(50, 30, 90, 20);
        setIcon(new ImageIcon(iconPath));
        setFont(AJAppStyle.FONT);
    }

    public void customizeComponent(String text) {
        setText(text);
        addMouseListener(this);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(AJAppStyle.COLOR_FONT);
        setHorizontalAlignment(AJAppStyle.ALIGNMENT_LEFT);
        setFont(AJAppStyle.FONT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Este evento no se utiliza en este componente.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Este evento no se utiliza en este componente.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Este evento no se utiliza en este componente.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
        setCursor(AJAppStyle.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
        setCursor(AJAppStyle.CURSOR_DEFAULT);
    }
}
