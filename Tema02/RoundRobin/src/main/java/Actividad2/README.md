¿Qué hace el programa?

Crea varios procesos con su nombre y el tiempo que necesitan para terminar.

Usa una cola (LinkedList) y un quantum para ir dando turnos a los procesos.

En cada turno resta el tiempo ejecutado (Math.min(quantum, tiempoRestante)) y actualiza el tiempo actual (t).

Si a un proceso aún le queda tiempo, vuelve al final de la cola; si llega a 0, termina.

Al final muestra el orden de ejecución, los intervalos de tiempo (t) y cuándo termina cada proceso.
