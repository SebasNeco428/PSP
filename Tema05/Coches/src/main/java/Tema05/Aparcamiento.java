
package Tema05;

import java.util.concurrent.Semaphore;

public class Aparcamiento {

    private Semaphore plazas;
    private boolean[] ocupadas = new boolean[3];
    public Aparcamiento() {
        plazas = new Semaphore(3, true);
    }

    public int entrar(int coche)
            throws InterruptedException {
        System.out.println("Coche " + coche + " quiere entrar");
        plazas.acquire();

// Buscar una plaza libre
        synchronized(this){
            for (int i = 0; i < ocupadas.length; i++) {
                if (!ocupadas[i]) {
                    ocupadas[i] = true;
                    System.out.println("Coche " + coche + " entra en plaza " + (i + 1));
                    return i;
                }
            }
        }
        return -1;
    }
    public synchronized void salir(int coche, int plaza) {
        ocupadas[plaza] = false;

        System.out.println("Coche " + coche + " sale de la plaza " + (plaza + 1));

        plazas.release();
    }
}