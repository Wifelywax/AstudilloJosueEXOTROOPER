
package AJInfrastructura;


import javax.swing.JOptionPane;

public abstract class AJAppMSG {
    private AJAppMSG() {
    }

    public static final void show(String msg) {
        JOptionPane.showMessageDialog(null, msg, "🐜 AntCiberDron", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "💀 AntCiberDron", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "🐜 AntCiberDron",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
