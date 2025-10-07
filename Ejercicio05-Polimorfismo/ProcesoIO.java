public class ProcesoIO extends Proceso{
    private DispositivoIO dispositivo;
    private int bytesAProcesar;

    public ProcesoIO(int pid, String nombre, DispositivoIO dispositivo, int bytes) {
        super(pid, nombre);
        this.dispositivo = dispositivo;
        this.bytesAProcesar = bytes;
    }

    public DispositivoIO getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoIO dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getBytesAProcesar() {
        return bytesAProcesar;
    }

    public void setBytesAProcesar(int bytes) {
        this.bytesAProcesar = bytes;
    }

    @Override
    public void ejecutar() {
        // Simula la ejecución del proceso IO
        System.out.println("Ejecutando Proceso IO: " + getNombre() + " en dispositivo " + dispositivo + " procesando " + bytesAProcesar + " bytes.");
    }

    @Override
    public String toString() {
        return super.toString() + " [dispositivo=" + dispositivo + ", bytesAProcesar=" + bytesAProcesar + "]";
        // Llama al toString() de la clase base y añade dispositivo y bytesAProcesar
    }
}