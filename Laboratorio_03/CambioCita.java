public class CambioCita {
    private final FechaHora fechaCambio;
    private final EstadoCita estadoAnterior;
    private final EstadoCita estadoNuevo;
    private final String motivo;

    public CambioCita(FechaHora fechaCambio, EstadoCita estadoAnterior,
                      EstadoCita estadoNuevo, String motivo)
            throws EntradaInvalidaException {
        if (fechaCambio == null)
            throw new EntradaInvalidaException("Fecha de cambio nula");
        if (estadoAnterior == null || estadoNuevo == null)
            throw new EntradaInvalidaException("Estados nulos");
        if (motivo == null || motivo.trim().isEmpty())
            throw new EntradaInvalidaException("Motivo vacío");

        this.fechaCambio = fechaCambio;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.motivo = motivo.trim();
    }

    // Getters
    public FechaHora getFechaCambio() {
        return fechaCambio;
    }

    public EstadoCita getEstadoAnterior() {
        return estadoAnterior;
    }

    public EstadoCita getEstadoNuevo() {
        return estadoNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Cambio{" +
                "fecha=" + fechaCambio +
                ", " + estadoAnterior + " → " + estadoNuevo +
                ", motivo='" + motivo + "'" +
                "}";
    }
}