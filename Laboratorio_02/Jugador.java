public class Jugador {
    private String nombre;
    private int puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void sumarPuntaje() {
        this.puntaje++;
    }
}