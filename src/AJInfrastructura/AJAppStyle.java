
package AJInfrastructura;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AJAppStyle {


 
    public static final Color COLOR_FONT = new Color(75, 0, 130); 
    
   
    public static final Color COLOR_FONT_LIGHT = new Color(70, 130, 180); 
    
  
    public static final Color COLOR_CURSOR = new Color(220, 20, 60); 
    
    
    public static final Color COLOR_BORDER = new Color(0, 0, 255); 

    public static final Color COLOR_BACKGROUND = new Color(255, 255, 255);
   
    private static final String FONT_FAMILY = "NovaMono";

    public static final Font FONT = new Font(FONT_FAMILY, Font.PLAIN, 20);
    public static final Font FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, 14); 
    public static final Font FONT_BOLD = new Font(FONT_FAMILY, Font.BOLD | Font.PLAIN, 20);

    public static final int ALIGNMENT_LEFT = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    private AJAppStyle() {
    }


    public static final CompoundBorder createBorderRect() {
      
        return BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDER, 2), 
                new EmptyBorder(5, 5, 5, 5)
        );
    }
}
