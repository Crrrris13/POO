public class EfectoBuffAtaque implements Efecto {
    private int turnosRestantes;
    private final int incremento;

    public EfectoBuffAtaque(int turnos, int incremento) {
        this.turnosRestantes = Math.max(1, turnos);
        this.incremento = Math.max(1, incremento);
    }

    @Override
    public void aplicar(Combatiente objetivo) {
        objetivo.ataqueBase += incremento;
    }

    @Override
    public void onInicioTurno(Combatiente objetivo) { }

    @Override
    public void onFinTurno(Combatiente objetivo) {
        turnosRestantes--;
        if (turnosRestantes == 0) {
            objetivo.ataqueBase = Math.max(1, objetivo.ataqueBase - incremento);
        }
    }

    @Override
    public int restante() { return turnosRestantes; }

    @Override
    public boolean estaActivo() { return turnosRestantes > 0; }
}
