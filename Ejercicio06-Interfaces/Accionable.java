public interface Accionable {
    //Ejecuta una accion especifica en el dispositivo
    boolean ejecutarAccion(String accion);

    //Obtiene la lista de de todas las acciones que el dispositivo puede realizar
    String[] getAccionesDisponibles();
}