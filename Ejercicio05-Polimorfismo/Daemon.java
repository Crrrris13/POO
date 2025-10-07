public class Daemon extends Proceso{
    private String servicio;
    private boolean activo;

    public Daemon(int pid, String nombre, String servicio, boolean activo) {
        super(pid, nombre);
        this.servicio = servicio;
        this.activo = activo; // Por defecto, el daemon no está activo
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public void ejecutar() {
        // Simula la ejecución del proceso Daemon
        if (activo) {
            System.out.println("Ejecutando Daemon: " + getNombre() + " proporcionando el servicio " + servicio + ".");
        } else {
            System.out.println("El Daemon: " + getNombre() + " está inactivo.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [servicio=" + servicio + ", activo=" + activo + "]";
        // Llama al toString() de la clase base y añade servicio y estado activo
    }
}