import java.util.List;

public abstract class Jugador extends Combatiente {
    protected Inventario inventario = new Inventario(10);

    public Jugador(String nombre, int vidaMax, int ataqueBase) {
        this.nombre = nombre;
        this.vidaMax = Math.max(1, vidaMax);
        this.vida = this.vidaMax;
        this.ataqueBase = Math.max(1, ataqueBase);
    }

    public void usarItem(Item item, List<Combatiente> objetivos, Batalla batalla) {
        if (item == null || !inventario.tiene(item)) return;
        item.usar(this, objetivos, batalla);
        if (item.getUsosRestantes() <= 0) inventario.remover(item);
    }

    public Inventario getInventario() { return inventario; }
}
