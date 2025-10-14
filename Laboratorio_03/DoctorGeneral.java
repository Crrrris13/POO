public class DoctorGeneral extends Medico {
    private int capacidadPacientesDia;
    private double tarifaConsulta;
    private int consultasRealizadas;

    public DoctorGeneral(String idEmpleado, String nombre, String departamento,
                  double salarioBase, String especializacion,
                  int capacidadPacientesDia, double tarifaConsulta)
            throws EntradaInvalidaException {
        super(idEmpleado, nombre, departamento, salarioBase);

        if (especializacion == null || especializacion.trim().isEmpty())
            throw new EntradaInvalidaException("Especialización vacía");
        if (capacidadPacientesDia <= 0)
            throw new EntradaInvalidaException("Capacidad debe ser positiva");
        if (tarifaConsulta < 0)
            throw new EntradaInvalidaException("Tarifa negativa");

        this.capacidadPacientesDia = capacidadPacientesDia;
        this.tarifaConsulta = tarifaConsulta;
        this.consultasRealizadas = 0;
    }

    //Se calcula salario base
    @Override
    public double calcularSalario() {
        return getSalarioBase() + (consultasRealizadas * tarifaConsulta);
    }

    public void registrarConsulta() {
        this.consultasRealizadas++;
    }

    public void resetearConsultas() {
        this.consultasRealizadas = 0;
    }

    // Getters

    public int getCapacidadPacientesDia() {
        return capacidadPacientesDia;
    }

    public double getTarifaConsulta() {
        return tarifaConsulta;
    }

    public int getConsultasRealizadas() {
        return consultasRealizadas;
    }

    // Setters
    public void setCapacidadPacientesDia(int capacidadPacientesDia)
            throws EntradaInvalidaException {
        if (capacidadPacientesDia <= 0)
            throw new EntradaInvalidaException("Capacidad debe ser positiva");
        this.capacidadPacientesDia = capacidadPacientesDia;
    }

    public void setTarifaConsulta(double tarifaConsulta) throws EntradaInvalidaException {
        if (tarifaConsulta < 0)
            throw new EntradaInvalidaException("Tarifa negativa");
        this.tarifaConsulta = tarifaConsulta;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getIdEmpleado() + "', " +
                "nombre='" + getNombre() + "', " +
                "capacidad=" + capacidadPacientesDia + " pac/día, " +
                "tarifa=Q" + String.format("%.2f", tarifaConsulta) + ", " +
                "consultas=" + consultasRealizadas + ", " +
                "salarioTotal=Q" + String.format("%.2f", calcularSalario()) +
                "}";
    }
}