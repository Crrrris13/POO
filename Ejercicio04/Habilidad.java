import java.util.List;

public interface Habilidad {
    void ejecutar(Combatiente origen, List<Combatiente> objetivos, Batalla batalla);
    String nombre();
    String descripcion();
}
