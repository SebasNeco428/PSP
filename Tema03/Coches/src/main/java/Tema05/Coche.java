package Tema05;

public class Coche implements Runnable {
    private int id;
    private Aparcamiento aparcamiento;
    public Coche(int id, Aparcamiento aparcamiento) {
        this.id = id;
        this.aparcamiento = aparcamiento;
    }
    @Override

    public void run() {
        int plaza = -1;
        try {
            plaza = aparcamiento.entrar(id);
// Permanece aparcado 3 segundos
            Thread.sleep(3000);
            aparcamiento.salir(id, plaza);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}