import javax.print.DocFlavor.READER;

public class ServicioAccion {
    public boolean ejecutar(Accionable accionable, String accion) {
        // Validación múltiple en una línea:
        // - accionable no debe ser null
        // - accion no debe ser null
        // - accion no debe estar vacía

        if (accionable == null || accion == null || accion.trim().isEmpty()) {
            return false; // Validacion Fallida
        }

        // Delegar la ejecución al dispositivo
        // El dispositivo sabe cómo ejecutar sus propias acciones
        return accionable.ejecutarAccion(accion);
    }

    public String[] accionesDisponibles(Accionable accionable) {
        if (accionable == null) {
            // Retornar array vacion 
            return new String[0];
        }

        return accionable.getAccionesDisponibles();
    }
}
