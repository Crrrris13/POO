public class ProcesoCPU extends Proceso{
    private int ciclosNecesarios;

    public ProcesoCPU(int pid, String nombre, int ciclosNecesarios) {
        super(pid, nombre);
        this.ciclosNecesarios = ciclosNecesarios;
    }

    public int getCiclosNecesarios() {
        return ciclosNecesarios;
    }

    public void setCiclosNecesarios(int ciclos) {
        this.ciclosNecesarios = ciclos;
    }

    @Override
    public void ejecutar() {
        // Simula la ejecución del proceso CPU
        System.out.println("Ejecutando Proceso CPU: " + getNombre() + " con " + ciclosNecesarios + " ciclos necesarios.");
    }

    @Override
    public String toString() {
        return super.toString() + " [ciclosNecesarios=" + ciclosNecesarios + "]";
        // Llama al toString() de la clase base y añade ciclosNecesarios
    }
}