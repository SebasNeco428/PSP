package Actividad2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Proceso {
    String nombre;
    int tiempoTotal;
    int tiempoRestante;

    public Proceso(String nombre, int tiempoTotal) {
        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
    }
}
public class RoundRobin {

    static void main(String[] args) {
        int quantum = 2;

        // 1. Creamos los procesos iniciales
        List<Proceso> procesosIniciales = new ArrayList<>();
        procesosIniciales.add(new Proceso("P1", 5));
        procesosIniciales.add(new Proceso("P2", 3));
        procesosIniciales.add(new Proceso("P3", 1));

        // 2. Cola para la planificación Round Robin
        Queue<Proceso> cola = new LinkedList<>(procesosIniciales);

        // Estructuras para guardar el historial y los tiempos de finalización
        List<String> ordenEjecucion = new ArrayList<>();
        List<String> intervalos = new ArrayList<>();
        Map<String, Integer> tiempoFin = new LinkedHashMap<>();

        int tiempoActual = 0;

        System.out.println("=== INICIO DE LA SIMULACIÓN ROUND ROBIN (Quantum = " + quantum + ") ===\n");

        // 3. Bucle principal hasta que no queden procesos en la cola
        while (!cola.isEmpty()) {
            Proceso actual = cola.poll();

            // 4. Tiempo real que se va a ejecutar en este turno
            int tiempoEjecutado = Math.min(quantum, actual.tiempoRestante);
            int inicioIntervalo = tiempoActual;

            actual.tiempoRestante -= tiempoEjecutado;
            tiempoActual += tiempoEjecutado;

            ordenEjecucion.add(actual.nombre);
            intervalos.add(actual.nombre + " [t = " + inicioIntervalo + " -> " + tiempoActual + "]");

            System.out.println("Ejecutando " + actual.nombre + " durante " + tiempoEjecutado
                    + " ud. | Intervalo t: [" + inicioIntervalo + " - " + tiempoActual + "]"
                    + " | Restante: " + actual.tiempoRestante);

            // 5. Comprobar si ha terminado o vuelve al final de la cola
            if (actual.tiempoRestante == 0) {
                tiempoFin.put(actual.nombre, tiempoActual);
            } else {
                cola.add(actual);
            }
        }

        // 6. Resumen final
        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Orden de ejecución (por turno): " + String.join(" -> ", ordenEjecucion));

        System.out.println("\nIntervalos de ejecución:");
        for (String intervalo : intervalos) {
            System.out.println("  - " + intervalo);
        }

        System.out.println("\nTiempo de finalización de cada proceso:");
        for (Map.Entry<String, Integer> entry : tiempoFin.entrySet()) {
            System.out.println("  - " + entry.getKey() + ": t = " + entry.getValue());
        }
    }
}