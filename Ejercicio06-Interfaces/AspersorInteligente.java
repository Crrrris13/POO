public class AspersorInteligente extends DispositivoAgricola 
                                 implements Accionable, Medible {
    private double rangoAlcance;
    private String ultimaMedicion;
    
    public AspersorInteligente(String id, String nombre, String fabricante, 
                               double consumoElectrico, String ubicacion, 
                               String fechaInstalacion, double rangoAlcance) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.rangoAlcance = rangoAlcance;
        this.ultimaMedicion = "Humedad local: " + (60 + Math.random() * 30) + "%";
    }
    
    public double getRangoAlcance() {
        return rangoAlcance;
    }
    
    @Override
    public String getMedicion() {
        return ultimaMedicion;
    }
    
    @Override
    public String getTipoMedicion() {
        return "Humedad local del área de riego";
    }
    
    @Override
    public boolean ejecutarAccion(String accion) {
        boolean exito = false;
        switch (accion.toLowerCase()) {
            case "regar":
            case "activar":
                exito = true;
                break;
            case "desactivar":
            case "pausar":
                exito = true;
                break;
            case "ajustar_rango":
                exito = true;
                break;
            default:
                exito = false;
        }
        return exito;
    }
    
    @Override
    public String[] getAccionesDisponibles() {
        return new String[]{"regar", "activar", "desactivar", "pausar", "ajustar_rango"};
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Alcance: %.1f m | %s", 
                                                rangoAlcance, ultimaMedicion);
    }
}