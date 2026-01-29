package AJApp.AJDesktopApp.CustomControl;

import AJInfrastructura.AJAppStyle;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class AJLabel extends JLabel {
    public AJLabel() {
        customizeComponent();
    }

    public AJLabel(String text) {
        setText(text);
        customizeComponent();
    }

    private void customizeComponent() {
        setCustomizeComponent(getText(), AJAppStyle.FONT, AJAppStyle.COLOR_FONT_LIGHT, AJAppStyle.ALIGNMENT_LEFT);
    }

    public void setCustomizeComponent(String text, Font font, Color color_font, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color_font);
        setHorizontalAlignment(alignment);
        // setIcon(new ImageIcon(iconPath));
    }
}