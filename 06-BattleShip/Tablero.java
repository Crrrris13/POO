import java.util.Random;

public class Tablero {

    private final int n;
    private final Celda[][] celdas;
    private int barcosTotales;


    public Tablero(int n){
        this.n = n;
        this.celdas = new Celda[n][n];
        this.barcosTotales = 0;
        this.initTablero(n);
    }

    private void initTablero(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.celdas[i][j] = new Celda();
            }
        }
    }

    public int getN() {
        return this.n;
    }

    public Celda getCelda(int i, int j) {
        return this.celdas[i][j];
    }

    public int getBarcosTotales() {
        return this.barcosTotales;
    }

    public void colocarBarcos(int cantidad, Random rnd) {

    }

    public boolean disparar(int i, int j) {


    }

    public boolean yaDisparada(int i, int j) {

    }

    public int barcosHundidos() {

    }

    public boolean todosHundidos() {

    }



}