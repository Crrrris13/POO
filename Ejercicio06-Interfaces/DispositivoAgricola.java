public abstract class DispositivoAgricola implements Comparable<DispositivoAgricola> {
    private String id;
    private String nombre;
    private String fabricante;
    private double consumoElectrico;
    private String ubicacion;
    private String fechaInstalacion;
    
    public DispositivoAgricola(String id, String nombre, String fabricante, 
                               double consumoElectrico, String ubicacion, 
                               String fechaInstalacion) {
        // Asignamos cada parámetro a su atributo correspondiente
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.consumoElectrico = consumoElectrico;
        this.ubicacion = ubicacion;
        this.fechaInstalacion = fechaInstalacion;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getFabricante() {
        return fabricante;
    }
    
    public double getConsumoElectrico() {
        return consumoElectrico;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public String getFechaInstalacion() {
        return fechaInstalacion;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setConsumoElectrico(double consumoElectrico) {
        this.consumoElectrico = consumoElectrico;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | %s | Fabricante: %s | Consumo: %.2f kWh | Ubicación: %s",
                           id,              // %s - ID del dispositivo
                           nombre,          // %s - Nombre
                           fabricante,      // %s - Fabricante
                           consumoElectrico, // %.2f - Consumo con 2 decimales
                           ubicacion);      // %s - Ubicación
    }
    
    @Override
    public boolean equals(Object obj) {
        // Caso 1: Comparación rápida - si es el mismo objeto en memoria
        if (this == obj) return true;
        
        // Caso 2: Si el objeto es null o de clase diferente
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Caso 3: Hacemos cast y comparamos por ID
        DispositivoAgricola otro = (DispositivoAgricola) obj;
        return this.id.equals(otro.id);
    }
    
    @Override
    public int compareTo(DispositivoAgricola otro) {
        return Double.compare(this.consumoElectrico, otro.consumoElectrico);
    }
}
