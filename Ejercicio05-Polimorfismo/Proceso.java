public abstract class Proceso {
    protected final int pid;
    private String nombre;

    public Proceso(int pid, String nombre) {
        this.pid = pid;
        this.nombre = nombre;
    }

    public int getPid() {
        return pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Polimorfismo: cada subclase implementa su propia versión de ejecutar()

    public abstract void ejecutar();

    // Muestra el tipo de proceso, PID y nombre
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [pid=" + pid + ", nombre=" + nombre + "]";

    }

    // Dos procesos son iguales si tienen el mismo PID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Proceso))
            return false;
        Proceso other = (Proceso) obj;
        return pid == other.pid;

    }

    // Hash basado en el PID
    @Override
    public int hashCode() {
        return Integer.hashCode(pid);

    }
}