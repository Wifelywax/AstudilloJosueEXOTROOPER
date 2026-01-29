package AJInfrastructura.AJTools;
//Refactorizacion
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExoMunicion {
    private static final String FILE = "AJStorage/AJDataFile/ExoMunision.txt";

    public static boolean hayMunicion(String tipoArma) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(tipoArma)) {
                    return true;
                }
            }
        } catch (IOException e) {
            CMD.print("ERROR : Lectura ExoMunision");
        }

        return false;
    }
}
