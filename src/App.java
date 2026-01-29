import AJApp.AJDesktopApp.Forms.AJExoTrooper;
import AJDataAccess.AJHelpers.AJDatabaseInitializer;
import AJInfrastructura.AJAppException;
import AJInfrastructura.AJTools.AJAuthService;

public class App {
    public static void main(String[] args) {
        try {
            // Autenticar usuario
            if (!AJAuthService.autenticar()) {
                System.exit(1);
            }

            // Inicializar base de datos
            AJDatabaseInitializer.initializeDatabase();

            java.awt.EventQueue.invokeLater(() -> {
                new AJExoTrooper().setVisible(true);
            });
        } catch (AJAppException e) {
            System.err.println("ERROR: No se pudo inicializar la base de datos");
            e.printStackTrace();
        }
    }
}