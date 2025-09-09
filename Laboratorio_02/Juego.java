public class Juego {
    private Jugador jugador;
    private Tablero tablero;

    public Juego(Jugador jugador, Tablero tablero) {
        this.jugador = jugador;
        this.tablero = tablero;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public Juego setJugador(Jugador jugador) {
        this.jugador = jugador;
        return this;
    }

    public Juego setTablero(Tablero tablero) {
        this.tablero = tablero;
        return this;
    }


    public boolean estaFinalizado() {
        return this.tablero.todasEmparejadas();
    }

    public String mostrarTablero() {
        return this.tablero.mostrarTablero();
    }
}
