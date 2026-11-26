package Tema05;

public class Main {
    static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento();
        for (int i = 1; i <= 6; i++) {
            Coche coche = new Coche(i, aparcamiento);
            Thread hilo = new Thread(coche);
            hilo.start();
        }
    }
}