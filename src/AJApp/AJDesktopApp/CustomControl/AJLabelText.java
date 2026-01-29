package AJApp.AJDesktopApp.CustomControl;

import AJInfrastructura.AJAppStyle;
import java.awt.*;
import javax.swing.*;

public class AJLabelText extends JPanel {
    private AJLabel lblEtiqueta = new AJLabel();
    private AJTextBox txtContenido = new AJTextBox();

    public AJLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        lblEtiqueta.setCustomizeComponent(etiqueta,
                AJAppStyle.FONT_SMALL,
                AJAppStyle.COLOR_FONT_LIGHT,
                AJAppStyle.ALIGNMENT_LEFT);
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }

    public String getText() {
        return txtContenido.getText();
    }
}
