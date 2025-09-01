import java.util.Random;

public class Battleship {

    private final int N = 5;
    private final int BARCOS = 3;
    private final int DISPAROS_MAX = 12;

    private final Tablero tablero;
    private final Consola vista;
    private final Random rnd = new Random();
    private int disparosUsados = 0;


    public Battleship(int n, int barcos, int disparosMax) {
        this.tablero = new Tablero(n);
        this.vista = new Consola();
    }

    public void jugar(){
        this.vista.dibujarTablero(this.tablero);
    }
}