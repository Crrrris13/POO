import java.util.ArrayList;
import java.util.List;

public abstract class Enemigo extends Combatiente {
    protected List<Habilidad> habilidades = new ArrayList<>();

    public Enemigo(String nombre, int vidaMax, int ataqueBase) {
        this.nombre = nombre;
        this.vidaMax = Math.max(1, vidaMax);
        this.vida = this.vidaMax;
        this.ataqueBase = Math.max(1, ataqueBase);
    }

    public void agregarHabilidad(Habilidad h) {
        if (h != null) habilidades.add(h);
    }

    public List<Habilidad> getHabilidades() { return habilidades; }

    public abstract void usarHabilidad(Batalla batalla);
}
