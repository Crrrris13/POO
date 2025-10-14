import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Manager del Hospital - Modelo en MVC
 * Maneja toda la lógica de negocio
 * @author [Tu nombre]
 */
public class HospitalManager {
    private final ArrayList<Medico> medicos;
    private final ArrayList<Cita> citas;
    private int contadorCitas;

    public HospitalManager() {
        this.medicos = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.contadorCitas = 1;
    }

    // ========== GESTIÓN DE MÉDICOS ==========

    /**
     * Registra un nuevo médico
     */
    public void registrarMedico(Medico medico) throws EntradaInvalidaException {
        if (medico == null)
            throw new EntradaInvalidaException("Médico nulo");

        // Verificar duplicados
        for (Medico m : medicos) {
            if (m.equals(medico)) {
                throw new EntradaInvalidaException(
                        "Ya existe un médico con ID " + medico.getIdEmpleado());
            }
        }

        medicos.add(medico);
        System.out.println("✅ Médico registrado: " + medico.getNombre());
    }

    /**
     * Busca médico por ID
     */
    public Medico buscarMedicoPorId(String id) throws EntidadNoEncontrada {
        if (id == null || id.trim().isEmpty())
            throw new EntidadNoEncontrada("ID vacío");

        for (Medico m : medicos) {
            if (m.getIdEmpleado().equals(id.trim())) {
                return m;
            }
        }
        throw new EntidadNoEncontrada("Médico no encontrado: " + id);
    }

    /**
     * Busca médicos disponibles por tipo y fecha
     */
    public List<Medico> buscarDisponibles(Class<?> tipoMedico, FechaHora fecha)
            throws EntradaInvalidaException {
        if (tipoMedico == null || fecha == null)
            throw new EntradaInvalidaException("Parámetros nulos");

        List<Medico> disponibles = new ArrayList<>();

        for (Medico medico : medicos) {
            // Verificar tipo
            if (!tipoMedico.isInstance(medico))
                continue;

            // Verificar disponibilidad (sin conflictos)
            boolean tieneConflicto = false;
            for (Cita cita : medico.getCitasAsignadas()) {
                if (cita.getFechaHoraProgramada().equals(fecha) &&
                        cita.getEstado() != EstadoCita.CANCELADA &&
                        cita.getEstado() != EstadoCita.COMPLETADA) {
                    tieneConflicto = true;
                    break;
                }
            }

            if (!tieneConflicto) {
                disponibles.add(medico);
            }
        }

        return disponibles;
    }

    // ========== GESTIÓN DE CITAS ==========

    /**
     * Programa una nueva cita
     */
    public Cita programarCita(String nombrePaciente, Medico medico,
                              FechaHora fecha, TipoCita tipo)
            throws EntradaInvalidaException, ConflictoCitaException {
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty())
            throw new EntradaInvalidaException("Nombre de paciente vacío");
        if (medico == null)
            throw new EntradaInvalidaException("Médico nulo");
        if (fecha == null)
            throw new EntradaInvalidaException("Fecha nula");
        if (tipo == null)
            throw new EntradaInvalidaException("Tipo nulo");

        // Detectar conflictos
        List<Cita> conflictos = detectarConflictos(medico, fecha);
        if (!conflictos.isEmpty()) {
            throw new ConflictoCitaException(
                    "El médico " + medico.getNombre() +
                            " ya tiene una cita programada en " + fecha);
        }

        // Crear cita
        String idCita = "C" + String.format("%04d", contadorCitas++);
        Cita nuevaCita = new Cita(idCita, nombrePaciente, medico, fecha, tipo);

        citas.add(nuevaCita);
        medico.asignarCita(nuevaCita);

        System.out.println("✅ Cita programada: " + idCita);
        return nuevaCita;
    }

    /**
     * Sobrecarga: programa cita con ID de médico
     */
    public Cita programarCita(String nombrePaciente, String idMedico,
                              FechaHora fecha, TipoCita tipo)
            throws EntradaInvalidaException, ConflictoCitaException, EntidadNoEncontrada {
        Medico medico = buscarMedicoPorId(idMedico);
        return programarCita(nombrePaciente, medico, fecha, tipo);
    }

    /**
     * Reagenda una cita existente
     */
    public void reagendarCita(String idCita, FechaHora nuevaFecha,
                              String motivo, FechaHora fechaActual)
            throws EntidadNoEncontrada, ConflictoCitaException, EntradaInvalidaException {
        Cita cita = buscarCitaPorId(idCita);

        // Verificar disponibilidad en nueva fecha
        List<Cita> conflictos = detectarConflictos(cita.getMedicoAsignado(), nuevaFecha);
        // Remover la cita actual de conflictos (porque se está moviendo)
        conflictos.remove(cita);

        if (!conflictos.isEmpty()) {
            throw new ConflictoCitaException(
                    "Conflicto detectado en nueva fecha: " + nuevaFecha);
        }

        cita.reagendar(nuevaFecha, motivo, fechaActual);
        System.out.println("✅ Cita reagendada correctamente");
    }

    /**
     * Busca cita por ID
     */
    public Cita buscarCitaPorId(String id) throws EntidadNoEncontrada {
        if (id == null || id.trim().isEmpty())
            throw new EntidadNoEncontrada("ID vacío");

        for (Cita c : citas) {
            if (c.getIdCita().equals(id.trim())) {
                return c;
            }
        }
        throw new EntidadNoEncontrada("Cita no encontrada: " + id);
    }

    /**
     * Detecta conflictos de horario para un médico
     */
    public List<Cita> detectarConflictos(Medico medico, FechaHora fecha) {
        List<Cita> conflictos = new ArrayList<>();

        for (Cita cita : medico.getCitasAsignadas()) {
            if (cita.getFechaHoraProgramada().equals(fecha) &&
                    cita.getEstado() != EstadoCita.CANCELADA &&
                    cita.getEstado() != EstadoCita.COMPLETADA) {
                conflictos.add(cita);
            }
        }

        return conflictos;
    }

    // ========== REPORTES ==========

    /**
     * Calcula nómina por departamento (¡Polimorfismo!)
     */
    public Map<String, Double> calcularNominaPorDepartamento() {
        Map<String, Double> nomina = new HashMap<>();

        for (Medico medico : medicos) {
            String depto = medico.getDepartamento();
            double salario = medico.calcularSalario(); // ¡Polimorfismo!

            nomina.put(depto, nomina.getOrDefault(depto, 0.0) + salario);
        }

        return nomina;
    }

    /**
     * Reporte de todo el personal
     */
    public void reportePersonal() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("REPORTE DE PERSONAL MÉDICO");
        System.out.println("=".repeat(80));

        if (medicos.isEmpty()) {
            System.out.println("No hay personal registrado");
        } else {
            for (Medico medico : medicos) {
                System.out.println(medico); // Polimorfismo en toString()
            }
        }
        System.out.println("=".repeat(80));
    }

    /**
     * Reporte de citas por estado
     */
    public void reporteCitasPorEstado(EstadoCita estado) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("REPORTE DE CITAS - Estado: " + estado);
        System.out.println("=".repeat(80));

        boolean encontradas = false;
        for (Cita cita : citas) {
            if (cita.getEstado() == estado) {
                System.out.println(cita);
                encontradas = true;
            }
        }

        if (!encontradas) {
            System.out.println("No hay citas con estado " + estado);
        }
        System.out.println("=".repeat(80));
    }

    /**
     * Muestra historial de cambios de una cita
     */
    public void mostrarHistorialCita(String idCita) throws EntidadNoEncontrada {
        Cita cita = buscarCitaPorId(idCita);

        System.out.println("\n📋 HISTORIAL DE CAMBIOS - Cita " + idCita);
        System.out.println("-".repeat(60));

        List<CambioCita> historial = cita.getHistorialCambios();
        if (historial.isEmpty()) {
            System.out.println("No hay cambios registrados");
        } else {
            for (CambioCita cambio : historial) {
                System.out.println(cambio);
            }
        }
        System.out.println("-".repeat(60));
    }

    // Getters para acceso controlado
    public List<Medico> getMedicos() {
        return new ArrayList<>(medicos);
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }
}