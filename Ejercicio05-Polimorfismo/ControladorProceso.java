import java.util.List;

public class ControladorProceso {
    private final Planificador planificador;
    private final FabricaProcesos fabrica;

    // Constructor que inicializa el planificador y la fábrica de procesos
    public ControladorProceso(Planificador planificador) {
        this.planificador = planificador;
        this.fabrica = new FabricaProcesos();
    }

    // Método para crear un proceso utilizando la fábrica de procesos
    public void registrarProceso(Proceso proceso) {
        planificador.registrar(proceso);
    }

    // Método para crear y registrar varios procesos a la vez
    public void registrarProceso(Proceso... varios) {
        planificador.registrar(varios);
    }

    // Método para buscar un proceso por su PID
    public void ejecutarPorPid(int pid) {
        Proceso proceso = planificador.buscar(pid);
        if (proceso != null) {
            planificador.ejecutar(proceso);
        } else {
            System.out.println("No se encontró ningún proceso con PID: " + pid);
        }
    }

    // Método para ejecutar todos los procesos registrados
    public void ejecutarTodos() {
        planificador.ejecutarTodos();
    }

    // Método para listar todos los procesos registrados
    public List<Proceso> listar() {
        return planificador.getProcesos();
    }

    // Método para verificar si un proceso con un PID específico existe
    public boolean existeProceso(int pid) {
        return planificador.buscar(pid) != null;
    }

    public FabricaProcesos getFabrica() {
        return fabrica;
    }

}