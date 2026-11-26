package Actividad2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Actividad2 {

     static void main(String[] args) {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder processBuilder;

        // 1 y 2. Crear ProcessBuilder según el sistema operativo
        if (sistemaOperativo.contains("win")) {
            processBuilder = new ProcessBuilder("cmd", "/c", "dir");
        } else {
            processBuilder = new ProcessBuilder("ls");
        }

        // Combina la salida estándar y la de error para no bloquear el buffer
        processBuilder.redirectErrorStream(true);

        try {
            // 3. Iniciar el proceso mediante start()
            Process proceso = processBuilder.start();

            // 4 y 5. Leer la salida línea por línea con BufferedReader e InputStreamReader
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // 6 y 7. Esperar a que el proceso termine y mostrar el código de salida
            int codigoSalida = proceso.waitFor();
            System.out.println("\nCódigo de salida: " + codigoSalida);

        } catch (IOException e) {
            System.err.println("Error de entrada/salida al ejecutar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("La espera del proceso fue interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}