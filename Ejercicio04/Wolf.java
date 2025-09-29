public class Wolf extends Enemigo {
    protected int agresividad;
    protected double probabilidadCritico;

    public Wolf(String nombre) {
        super(nombre, 80, 10);
        this.agresividad = 3;
        this.probabilidadCritico = 0.15;
        // Puedes agregar habilidades genéricas si modelas como objetos:
        // habilidades.add(new HabilidadMordidaEnvenenada());
        // habilidades.add(new HabilidadEsquivarTemporal());
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: elegir entre mordidaEnvenenada o esquivarTemporal según situación
    }

    public void mordidaEnvenenada(Jugador objetivo) {
        if (objetivo == null || !estaVivo()) return;
        objetivo.recibirDanio(ataqueBase + agresividad);
        // TODO: si usas efectos, p.ej. aplicar veneno
    }

    public void esquivarTemporal() {
        // TODO: subir evasión por 1 turno (puede ser un Efecto)
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        // TODO
    }
}
