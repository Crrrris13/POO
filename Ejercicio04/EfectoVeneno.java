public class EfectoVeneno implements Efecto {
    private int turnosRestantes;
    private final int danioPorTurno;

    public EfectoVeneno(int turnos, int dpt) {
        this.turnosRestantes = Math.max(1, turnos);
        this.danioPorTurno = Math.max(1, dpt);
    }

    @Override
    public void aplicar(Combatiente objetivo) { }

    @Override
    public void onInicioTurno(Combatiente objetivo) {
        if (objetivo != null && objetivo.estaVivo()) {
            objetivo.recibirDanio(danioPorTurno);
        }
    }

    @Override
    public void onFinTurno(Combatiente objetivo) {
        turnosRestantes--;
    }

    @Override
    public int restante() { return turnosRestantes; }

    @Override
    public boolean estaActivo() { return turnosRestantes > 0; }
}
