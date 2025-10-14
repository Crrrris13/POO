import java.util.ArrayList;
import java.util.List;

public class Cita {
    private final String idCita;
    private String nombrePaciente;
    private Medico medicoAsignado;
    private FechaHora fechaHoraProgramada;
    private TipoCita tipo;
    private EstadoCita estado;
    private final ArrayList<CambioCita> historialCambios;

    public Cita(String idCita, String nombrePaciente, Medico medicoAsignado,
                FechaHora fechaHoraProgramada, TipoCita tipo)
            throws EntradaInvalidaException {
        if (idCita == null || idCita.trim().isEmpty())
            throw new EntradaInvalidaException("ID de cita vacío");
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty())
            throw new EntradaInvalidaException("Nombre de paciente vacío");
        if (medicoAsignado == null)
            throw new EntradaInvalidaException("Médico nulo");
        if (fechaHoraProgramada == null)
            throw new EntradaInvalidaException("Fecha nula");
        if (tipo == null)
            throw new EntradaInvalidaException("Tipo de cita nulo");

        this.idCita = idCita.trim();
        this.nombrePaciente = nombrePaciente.trim();
        this.medicoAsignado = medicoAsignado;
        this.fechaHoraProgramada = fechaHoraProgramada;
        this.tipo = tipo;
        this.estado = EstadoCita.PROGRAMADA;
        this.historialCambios = new ArrayList<>();
    }

    //Cambia el estado de la cita y registra el cambio
    public void cambiarEstado(EstadoCita nuevoEstado, String motivo, FechaHora fechaActual)
            throws EntradaInvalidaException {
        if (nuevoEstado == null)
            throw new EntradaInvalidaException("Estado nulo");
        if (this.estado == nuevoEstado)
            return; // No hay cambio real

        // Registrar cambio en historial
        CambioCita cambio = new CambioCita(fechaActual, this.estado, nuevoEstado, motivo);
        historialCambios.add(cambio);

        // Actualizar estado
        this.estado = nuevoEstado;

        // Notificación simulada
        System.out.println("NOTIFICACIÓN: Cita " + idCita +
                " cambió de " + cambio.getEstadoAnterior() +
                " a " + nuevoEstado);
    }

    //Reagenda la cita a una nueva fecha
    public void reagendar(FechaHora nuevaFecha, String motivo, FechaHora fechaActual)
            throws EntradaInvalidaException {
        if (nuevaFecha == null)
            throw new EntradaInvalidaException("Nueva fecha nula");

        FechaHora fechaAnterior = this.fechaHoraProgramada;
        this.fechaHoraProgramada = nuevaFecha;
        cambiarEstado(EstadoCita.REAGENDADA, motivo, fechaActual);

        System.out.println("📅 Cita reagendada: " + fechaAnterior + " → " + nuevaFecha);
    }

    // Getters
    public String getIdCita() {
        return idCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public FechaHora getFechaHoraProgramada() {
        return fechaHoraProgramada;
    }

    public TipoCita getTipo() {
        return tipo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public List<CambioCita> getHistorialCambios() {
        return new ArrayList<>(historialCambios);
    }

    // Setters
    public void setNombrePaciente(String nombrePaciente) throws EntradaInvalidaException {
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty())
            throw new EntradaInvalidaException("Nombre vacío");
        this.nombrePaciente = nombrePaciente.trim();
    }

    public void setMedicoAsignado(Medico medicoAsignado) throws EntradaInvalidaException {
        if (medicoAsignado == null)
            throw new EntradaInvalidaException("Médico nulo");
        this.medicoAsignado = medicoAsignado;
    }

    public void setFechaHoraProgramada(FechaHora fechaHoraProgramada)
            throws EntradaInvalidaException {
        if (fechaHoraProgramada == null)
            throw new EntradaInvalidaException("Fecha nula");
        this.fechaHoraProgramada = fechaHoraProgramada;
    }

    public void setTipo(TipoCita tipo) throws EntradaInvalidaException {
        if (tipo == null)
            throw new EntradaInvalidaException("Tipo nulo");
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id='" + idCita + "', " +
                "paciente='" + nombrePaciente + "', " +
                "médico='" + medicoAsignado.getNombre() + "', " +
                "fecha=" + fechaHoraProgramada + ", " +
                "tipo=" + tipo + ", " +
                "estado=" + estado +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cita)) return false;
        Cita c = (Cita) o;
        return this.idCita.equals(c.idCita);
    }

    @Override
    public int hashCode() {
        return idCita.hashCode();
    }
}