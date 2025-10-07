public class FabricaProcesos {
    // Nuevo método para crear un proceso CPU
    public ProcesoCPU crearCPU(int pid, String nombre, int ciclos) {
        return new ProcesoCPU(pid, nombre, ciclos);
    }


    // Nuevo método para crear un proceso IO
    public ProcesoIO crearIO(int pid, String nombre, DispositivoIO dispositivo, int bytes) {
        return new ProcesoIO(pid, nombre, dispositivo, bytes);
    }

    // Nuevo método para crear un proceso Daemon
    public Daemon crearDaemon(int pid, String nombre, String servicio, boolean activo) {
        return new Daemon(pid, nombre, servicio, activo);
    }

    // Método genérico que crea un proceso según el tipo especificado
    public Proceso crear(TipoProceso tipo, int pid, String nombre) {
        switch (tipo) {
            case CPU:
                return crearCPU(pid, nombre, 100); // Ciclos por defecto
            case IO:
                return crearIO(pid, nombre, DispositivoIO.PANTALLA, 1024);// Dispositivo y bytes por defecto
            case DAEMON:
                return crearDaemon(pid, nombre, "Servicio por defecto", true); // Servicio y estado por defecto
            default:
                throw new IllegalArgumentException("Tipo de proceso no soportado: " + tipo);
        }
    }
}