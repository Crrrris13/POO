import java.util.ArrayList;
import java.util.List;

public class ControladorBatalla {
    private final Batalla batalla;
    private final VistaBatalla vista;
    private boolean enEjecucion = false;

    public ControladorBatalla(Batalla b, VistaBatalla v) {
        this.batalla = b;
        this.vista = v;
    }

    public void ejecutar() {
        enEjecucion = true;
        batalla.iniciar();
        while (enEjecucion && !batalla.concluida()) {
            vista.mostrarEstado(batalla);
            vista.mostrarRegistro(batalla.getRegistro());

            // pedir acción del jugador
            AccionJugador accion = vista.pedirAccion(batalla.getJugador(), batalla);
            switch (accion.getTipo()) {
                case ATACAR -> {
                    List<Combatiente> candidatos = new ArrayList<>(batalla.vivosEnemigos());
                    List<Combatiente> objetivos = vista.pedirObjetivos(candidatos, 1);
                    if (!objetivos.isEmpty()) {
                        batalla.getJugador().atacar(objetivos.get(0));
                        batalla.getRegistro().registrar(batalla.getJugador().getNombre() + " ataca a " + objetivos.get(0).getNombre());
                    }
                }
                case USAR_ITEM -> {
                    Item item = vista.pedirItem(batalla.getJugador().getInventario());
                    if (item != null) {
                        // por simplicidad, item a un objetivo si es single-target
                        List<Combatiente> candidatos = new ArrayList<>();
                        candidatos.add(batalla.getJugador()); // permitir items sobre sí mismo
                        candidatos.addAll(batalla.vivosEnemigos()); // y/o enemigos si aplica
                        List<Combatiente> objetivos = vista.pedirObjetivos(candidatos, 1);
                        batalla.getJugador().usarItem(item, objetivos, batalla);
                        batalla.getRegistro().registrar(batalla.getJugador().getNombre() + " usa " + item.getClass().getSimpleName());
                    }
                }
                case PASAR -> batalla.getRegistro().registrar("El jugador pasa turno.");
                case HABILIDAD_ESPECIAL -> {
                    // TODO: si defines habilidades del jugador, ejecútalas
                    batalla.getRegistro().registrar("Habilidad especial (pendiente).");
                }
                case SALIR -> enEjecucion = false;
            }

            // turno de enemigos simples
            for (Enemigo e : batalla.getEnemigos()) {
                if (!e.estaVivo()) continue;
                // Ejemplo: enemigo ataca al jugador
                e.atacar(batalla.getJugador());
                batalla.getRegistro().registrar(e.getNombre() + " ataca a " + batalla.getJugador().getNombre());
                if (!batalla.getJugador().estaVivo()) {
                    batalla.declararDerrota();
                    break;
                }
            }

            // comprobar fin
            if (batalla.vivosEnemigos().isEmpty()) batalla.declararVictoria();
            if (!batalla.getJugador().estaVivo()) batalla.declararDerrota();
        }

        vista.mostrarEstado(batalla);
        vista.mostrarRegistro(batalla.getRegistro());
    }
}
