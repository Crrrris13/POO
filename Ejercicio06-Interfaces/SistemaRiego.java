public class SistemaRiego extends DispositivoAgricola implements Accionable {
    private int numeroValvulas;
    private double presionAgua;
    
    public SistemaRiego(String id, String nombre, String fabricante, 
                        double consumoElectrico, String ubicacion, 
                        String fechaInstalacion, int numeroValvulas, 
                        double presionAgua) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.numeroValvulas = numeroValvulas;
        this.presionAgua = presionAgua;
    }
    
    public int getNumeroValvulas() {
        return numeroValvulas;
    }
    
    public double getPresionAgua() {
        return presionAgua;
    }
    
    @Override
    public boolean ejecutarAccion(String accion) {
        boolean exito = false;
        switch (accion.toLowerCase()) {
            case "abrir_valvulas":
            case "regar":
                exito = true;
                break;
            case "cerrar_valvulas":
                exito = true;
                break;
            case "ajustar_presion":
                exito = true;
                break;
            default:
                exito = false;
        }
        return exito;
    }
    
    @Override
    public String[] getAccionesDisponibles() {
        return new String[]{"abrir_valvulas", "cerrar_valvulas", "ajustar_presion", "regar"};
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Válvulas: %d | Presión: %.1f PSI", 
                                                numeroValvulas, presionAgua);
    }
}