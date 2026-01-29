//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package AJInfrastructura;   
import AJInfrastructura.AJTools.CMD;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public abstract class AJAppConfig {

    private static final Properties props = new Properties();

    private static final String APP_PROPERTIES = "app.properties";
    private static final String KEY_DB_NAME = "db.File";
    private static final String KEY_FILE_LOG = "df.logFile";
    private static final String KEY_RES_IMG_MAIN = "res.img.Main";
    private static final String KEY_RES_IMG_LOGO = "res.img.Logo";
    private static final String KEY_RES_IMG_SPLASH = "res.img.Splash";

    
    public static String getDATABASE() {
        return getProperty(KEY_DB_NAME);
    }

    public static String getLOGFILE() {
        return getProperty(KEY_FILE_LOG);
    }

   
    public static URL getImgMain() {
        return getAppResource(KEY_RES_IMG_MAIN);
    }

    public static URL getImgLogo() {
        return getAppResource(KEY_RES_IMG_LOGO);
    }

    public static URL getImgSplash() {
        return getAppResource(KEY_RES_IMG_SPLASH);
    }

   
    public static final String MSG_DEFAULT_ERROR = "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    public static final String MSG_DEFAULT_CLASS = "undefined";
    public static final String MSG_DEFAULT_METHOD = "undefined";

    
   static {
     
        try (InputStream in = AJAppConfig.class.getResourceAsStream("app.properties")) {

            if (in == null) {
                // Si entra aquí, imprime la ruta para depurar
                CMD.print("ERROR CRÍTICO: No encuentro app.properties junto a AJAppConfig.class");
                throw new RuntimeException("app.properties no encontrado en el paquete AJInfrastructura");
            }

            props.load(in);
            CMD.print("AppConfig ❱❱ app.properties cargado correctamente");

        } catch (Exception e) {
            CMD.print("ERROR al cargar ❱❱ " + e.getMessage());
            e.printStackTrace(); // Esto nos dará más detalles si falla
        }
    }

    private AJAppConfig() {
    }

    static String getProperty(String key) {
        String value = props.getProperty(key);

        CMD.print("AppConfig ❱❱ " + APP_PROPERTIES + "." + key + " : " + value);

        if (value == null) {
            CMD.print("ERROR ❱❱ Propiedad no encontrada: " + key);
        }

        return value;
    }

    static URL getAppResource(String key) {
        String path = getProperty(key);
        if (path == null)
            return null;

    
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        URL res = AJAppConfig.class.getResource(path);

        if (res == null) {
            CMD.print("ERROR ❱❱ Recurso no encontrado en classpath: " + path);
        }

        return res;
    }
}
