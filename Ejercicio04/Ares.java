public class Ares extends Enemigo {
    protected int fuerzaBruta;
    protected int resistencia;

    public Ares(String nombre) {
        super(nombre, 110, 13);
        this.fuerzaBruta = 6;
        this.resistencia = 4;
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: elegir entre golpeFuerte o provocacion
    }

    public void golpeFuerte(Jugador objetivo) {
        if (objetivo == null) return;
        // TODO: daño que ignora parte de defensa (si modelas defensa)
        objetivo.recibirDanio(ataqueBase + fuerzaBruta);
    }

    public void provocacion(Jugador objetivo) {
        // TODO: forzar al jugador a atacarlo (marca/estado temporal)
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        // TODO
    }
}
