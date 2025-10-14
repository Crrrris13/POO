public class Enfermero extends Medico {
    private Turno tipoTurno;
    private String nivelCertificacion;
    private double bonificacionNocturna;

    public Enfermero(String idEmpleado, String nombre, String departamento, double salarioBase, Turno tipoTurno, String nivelCertificacion)
            throws EntradaInvalidaException {
        super(idEmpleado, nombre, departamento, salarioBase);

        if (tipoTurno == null)
            throw new EntradaInvalidaException("Turno nulo");
        if (nivelCertificacion == null || nivelCertificacion.trim().isEmpty())
            throw new EntradaInvalidaException("Certificación vacía");

        this.tipoTurno = tipoTurno;
        this.nivelCertificacion = nivelCertificacion.trim();
        this.bonificacionNocturna = calcularBonificacion();
    }

    private double calcularBonificacion() {
        return (tipoTurno == Turno.NOCTURNO) ? getSalarioBase() * 0.20 : 0.0;
    }

    //Se calculo el salario
    @Override
    public double calcularSalario() {
        return getSalarioBase() + bonificacionNocturna;
    }

    // Getters
    public Turno getTipoTurno() {
        return tipoTurno;
    }

    public String getNivelCertificacion() {
        return nivelCertificacion;
    }

    public double getBonificacionNocturna() {
        return bonificacionNocturna;
    }

    // Setters
    public void setTipoTurno(Turno tipoTurno) throws EntradaInvalidaException {
        if (tipoTurno == null)
            throw new EntradaInvalidaException("Turno nulo");
        this.tipoTurno = tipoTurno;
        this.bonificacionNocturna = calcularBonificacion();
    }

    public void setNivelCertificacion(String nivelCertificacion)
            throws EntradaInvalidaException {
        if (nivelCertificacion == null || nivelCertificacion.trim().isEmpty())
            throw new EntradaInvalidaException("Certificación vacía");
        this.nivelCertificacion = nivelCertificacion.trim();
    }

    @Override
    public String toString() {
        return "Enfermero{" +
                "id='" + getIdEmpleado() + "', " +
                "nombre='" + getNombre() + "', " +
                "turno=" + tipoTurno + ", " +
                "certificación='" + nivelCertificacion + "', " +
                "bonificación=Q" + String.format("%.2f", bonificacionNocturna) + ", " +
                "salarioTotal=Q" + String.format("%.2f", calcularSalario()) +
                "}";
    }
}