package AJInfrastructura.AJTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CMD {

    private static final String TRACE_FILE = "AJStorage/AJDataFile/ExoTracer.txt";

    public static void print(String msg) {

        // Consola
        System.out.println(msg);

        // Archivo tracer
        try (FileWriter fw = new FileWriter(TRACE_FILE, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println(msg);

        } catch (IOException e) {
            System.out.println("ERROR : No se pudo escribir en ExoTracer.txt");
        }
    }
}
