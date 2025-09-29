import java.util.ArrayList;
import java.util.List;

public class Batalla {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private int indiceTurno = 0;
    private RegistroAcciones registro = new RegistroAcciones(3);
    private EstadoBatalla estado = EstadoBatalla.EN_CURSO;

    public Batalla(Jugador jugador, List<Enemigo> enemigos) {
        this.jugador = jugador;
        this.enemigos = (enemigos == null) ? new ArrayList<>() : enemigos;
    }

    public void iniciar() {
        registro.registrar(jugador.mensajeInicio());
        for (Enemigo e : enemigos) registro.registrar(e.mensajeInicio());
    }

    public void siguienteTurno() {
        if (concluida()) return;
        // TODO: ciclo de turnos: jugador <-> enemigos
    }

    public List<Enemigo> vivosEnemigos() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo e : enemigos) if (e.estaVivo()) vivos.add(e);
        return vivos;
    }

    public boolean jugadorVivo() { return jugador != null && jugador.estaVivo(); }

    public boolean concluida() { return estado != EstadoBatalla.EN_CURSO; }

    public RegistroAcciones getRegistro() { return registro; }

    public EstadoBatalla getEstado() { return estado; }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }

    // Helpers para fijar victoria/derrota
    public void declararVictoria() { estado = EstadoBatalla.VICTORIA; }
    public void declararDerrota() { estado = EstadoBatalla.DERROTA; }
}
