import java.util.ArrayList;
import java.util.List;

public abstract class Combatiente {
    protected String nombre;
    protected int vidaMax;
    protected int vida;
    protected int ataqueBase;
    protected List<Efecto> efectos = new ArrayList<>();

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }

    public boolean estaVivo() { return vida > 0; }

    public void atacar(Combatiente objetivo) {
        if (objetivo == null || !estaVivo()) return;
        // TODO: agrega críticos, evasión, etc. según tu diseño
        objetivo.recibirDanio(Math.max(1, this.ataqueBase));
    }

    public void recibirDanio(int cantidad) {
        if (cantidad <= 0) return;
        vida = Math.max(0, vida - cantidad);
    }

    public void aplicarEfectosInicioTurno() {
        for (Efecto e : efectos) e.onInicioTurno(this);
        efectos.removeIf(e -> !e.estaActivo());
    }

    public void aplicarEfectosFinTurno() {
        for (Efecto e : efectos) e.onFinTurno(this);
        efectos.removeIf(e -> !e.estaActivo());
    }

    public abstract void tomarTurno(Batalla batalla);

    public String mensajeInicio() { return nombre + " entra en batalla."; }
    public String mensajeDerrota() { return nombre + " ha sido derrotado."; }
    public String mensajeVictoria() { return nombre + " ha ganado la batalla."; }
}
