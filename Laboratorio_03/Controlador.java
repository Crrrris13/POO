import java.util.List;
import java.util.Map;

/**
 * Controlador del sistema - Controlador en MVC
 * Coordina Modelo y Vista
 * @author [Tu nombre]
 */
public class Controlador {
    private final HospitalManager manager;
    private final Vista vista;

    public Controlador() {
        this.manager = new HospitalManager();
        this.vista = new Vista();
    }

    /**
     * Inicia el sistema
     */
    public void iniciar() {
        vista.mostrarMensaje("\nBienvenido al Sistema de Gestión Hospitalaria");

        boolean continuar = true;
        while (continuar) {
            try {
                vista.mostrarMenu();
                vista.mostrarMensaje("Seleccione una opción: ");
                int opcion = vista.leerEntero();

                switch (opcion) {
                    case 1:
                        manejarRegistroMedico();
                        break;
                    case 2:
                        manejarProgramacionCita();
                        break;
                    case 3:
                        manejarReagendamiento();
                        break;
                    case 4:
                        manejarCambioEstado();
                        break;
                    case 5:
                        manejarBusquedaDisponible();
                        break;
                    case 6:
                        manager.reportePersonal();
                        vista.pausar();
                        break;
                    case 7:
                        manejarReporteCitas();
                        break;
                    case 8:
                        manejarCalculoNomina();
                        break;
                    case 9:
                        manejarHistorialCita();
                        break;
                    case 10:
                        manejarRegistroActividad();
                        break;
                    case 0:
                        vista.mostrarMensaje("\n¡Hasta luego!");
                        continuar = false;
                        break;
                    default:
                        vista.mostrarError("Opción inválida");
                }
            } catch (Exception e) {
                vista.mostrarError(e.getMessage());
                vista.pausar();
            }
        }

        vista.cerrar();
    }

    /**
     * Maneja el registro de médicos
     */
    private void manejarRegistroMedico() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("REGISTRO DE MÉDICO");
            vista.mostrarSeparador();

            int tipo = vista.mostrarMenuTipoMedico();

            // Datos comunes
            String id = vista.leerString("ID de empleado: ");
            String nombre = vista.leerString("Nombre completo: ");
            String departamento = vista.leerString("Departamento: ");
            double salarioBase = vista.leerDouble("Salario base: Q");

            Medico medico = null;

            switch (tipo) {
                case 1: // Doctor
                    String especializacion = vista.leerString("Especialización: ");
                    int capacidad = vista.leerEntero("Capacidad pacientes/día: ");
                    double tarifaConsulta = vista.leerDouble("Tarifa por consulta: Q");
                    medico = new DoctorGeneral(id, nombre, departamento, salarioBase,
                            especializacion, capacidad, tarifaConsulta);
                    break;

                case 2: // Cirujano
                    double tarifaHora = vista.leerDouble("Tarifa por hora de cirugía: Q");
                    double bono = vista.leerDouble("Bono por riesgo: Q");
                    medico = new Cirujano(id, nombre, departamento, salarioBase,
                            tarifaHora, bono);

                    int numOps = vista.leerEntero("¿Cuántos tipos de operaciones?: ");
                    for (int i = 0; i < numOps; i++) {
                        String op = vista.leerString("Operación " + (i+1) + ": ");
                        ((Cirujano) medico).agregarTipoOperacion(op);
                    }
                    break;

                case 3: // Enfermero
                    Turno turno = vista.seleccionarTurno();
                    String certificacion = vista.leerString("Nivel de certificación: ");
                    medico = new Enfermero(id, nombre, departamento, salarioBase,
                            turno, certificacion);
                    break;

                case 4: // Radiólogo
                    double tarifaEstudio = vista.leerDouble("Tarifa por estudio: Q");
                    medico = new Radiologo(id, nombre, departamento, salarioBase,
                            tarifaEstudio);

                    int numEquipos = vista.leerEntero("¿Cuántos equipos certificados?: ");
                    for (int i = 0; i < numEquipos; i++) {
                        String equipo = vista.leerString("Equipo " + (i+1) + ": ");
                        ((Radiologo) medico).agregarEquipoCertificado(equipo);
                    }
                    break;

                default:
                    vista.mostrarError("Tipo inválido");
                    return;
            }

            if (medico != null) {
                manager.registrarMedico(medico);
                vista.mostrarExito("Médico registrado exitosamente");
            }

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja la programación de citas
     */
    private void manejarProgramacionCita() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("📅 PROGRAMAR CITA");
            vista.mostrarSeparador();

            String nombrePaciente = vista.leerString("Nombre del paciente: ");
            String idMedico = vista.leerString("ID del médico: ");
            FechaHora fecha = vista.leerFechaHora();
            TipoCita tipo = vista.seleccionarTipoCita();

            Cita cita = manager.programarCita(nombrePaciente, idMedico, fecha, tipo);
            vista.mostrarExito("Cita programada: " + cita.getIdCita());

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el reagendamiento de citas
     */
    private void manejarReagendamiento() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("🔄 REAGENDAR CITA");
            vista.mostrarSeparador();

            String idCita = vista.leerString("ID de la cita: ");
            FechaHora nuevaFecha = vista.leerFechaHora();
            String motivo = vista.leerString("Motivo del cambio: ");
            FechaHora fechaActual = vista.obtenerFechaActual();

            manager.reagendarCita(idCita, nuevaFecha, motivo, fechaActual);
            vista.mostrarExito("Cita reagendada exitosamente");

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el cambio de estado de citas
     */
    private void manejarCambioEstado() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("🔄 CAMBIAR ESTADO DE CITA");
            vista.mostrarSeparador();

            String idCita = vista.leerString("ID de la cita: ");
            Cita cita = manager.buscarCitaPorId(idCita);

            vista.mostrarMensaje("Estado actual: " + cita.getEstado());
            EstadoCita nuevoEstado = vista.seleccionarEstadoCita();
            String motivo = vista.leerString("Motivo del cambio: ");
            FechaHora fechaActual = vista.obtenerFechaActual();

            cita.cambiarEstado(nuevoEstado, motivo, fechaActual);
            vista.mostrarExito("Estado cambiado exitosamente");

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja la búsqueda de personal disponible
     */
    private void manejarBusquedaDisponible() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("🔍 BUSCAR PERSONAL DISPONIBLE");
            vista.mostrarSeparador();

            vista.mostrarMensaje("\nTipos de médico:");
            vista.mostrarMensaje("1. Doctor");
            vista.mostrarMensaje("2. Cirujano");
            vista.mostrarMensaje("3. Enfermero");
            vista.mostrarMensaje("4. Radiologo");
            vista.mostrarMensaje("5. Farmaceutico");
            vista.mostrarMensaje("6. Terapeuta");
            int tipoOp = vista.leerEntero("Seleccione tipo: ");

            Class<?> tipoClase = null;
            switch (tipoOp) {
                case 1: tipoClase = DoctorGeneral.class; break;
                case 2: tipoClase = Cirujano.class; break;
                case 3: tipoClase = Enfermero.class; break;
                case 4: tipoClase = Radiologo.class; break;
                default:
                    vista.mostrarError("Tipo inválido");
                    return;
            }

            FechaHora fecha = vista.leerFechaHora();
            List<Medico> disponibles = manager.buscarDisponibles(tipoClase, fecha);

            if (disponibles.isEmpty()) {
                vista.mostrarMensaje("No hay personal disponible");
            } else {
                vista.mostrarMensaje("\nPersonal disponible (" +
                        disponibles.size() + "):");
                for (Medico m : disponibles) {
                    vista.mostrarMensaje("  • " + m.getNombre() +
                            " (ID: " + m.getIdEmpleado() + ")");
                }
            }

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el reporte de citas por estado
     */
    private void manejarReporteCitas() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("REPORTE DE CITAS");
            vista.mostrarSeparador();

            EstadoCita estado = vista.seleccionarEstadoCita();
            manager.reporteCitasPorEstado(estado);

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el cálculo de nómina
     */
    private void manejarCalculoNomina() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("CÁLCULO DE NÓMINA POR DEPARTAMENTO");
            vista.mostrarSeparador();

            Map<String, Double> nomina = manager.calcularNominaPorDepartamento();

            if (nomina.isEmpty()) {
                vista.mostrarMensaje("No hay datos de nómina");
            } else {
                double total = 0;
                for (Map.Entry<String, Double> entry : nomina.entrySet()) {
                    String linea = String.format("%-30s Q%,10.2f",
                            entry.getKey() + ":",
                            entry.getValue());
                    vista.mostrarMensaje(linea);
                    total += entry.getValue();
                }
                vista.mostrarSeparador();
                String lineaTotal = String.format("%-30s Q%,10.2f",
                        "TOTAL GENERAL:", total);
                vista.mostrarMensaje(lineaTotal);
            }

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el historial de cambios de una cita
     */
    private void manejarHistorialCita() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("HISTORIAL DE CAMBIOS");
            vista.mostrarSeparador();

            String idCita = vista.leerString("ID de la cita: ");
            manager.mostrarHistorialCita(idCita);

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

    /**
     * Maneja el registro de actividades
     */
    private void manejarRegistroActividad() {
        try {
            vista.mostrarSeparador();
            vista.mostrarMensaje("REGISTRAR ACTIVIDAD");
            vista.mostrarSeparador();

            String idMedico = vista.leerString("ID del médico: ");
            Medico medico = manager.buscarMedicoPorId(idMedico);

            // Polimorfismo: dependiendo del tipo, registrar actividad
            if (medico instanceof DoctorGeneral) {
                int consultas = vista.leerEntero("¿Cuántas consultas realizó?: ");
                for (int i = 0; i < consultas; i++) {
                    ((DoctorGeneral) medico).registrarConsulta();
                }
                vista.mostrarExito(consultas + " consultas registradas");

            } else if (medico instanceof Cirujano) {
                int horas = vista.leerEntero("¿Cuántas horas de cirugía?: ");
                ((Cirujano) medico).registrarHorasCirugia(horas);
                vista.mostrarExito(horas + " horas registradas");

            } else if (medico instanceof Radiologo) {
                int estudios = vista.leerEntero("¿Cuántos estudios realizó?: ");
                for (int i = 0; i < estudios; i++) {
                    ((Radiologo) medico).registrarEstudio();
                }
                vista.mostrarExito(estudios + " estudios registrados");

            } else if (medico instanceof Enfermero) {
                vista.mostrarMensaje("ℹLos enfermeros tienen salario fijo");
            }

            vista.mostrarMensaje("\nNuevo salario: Q" +
                    String.format("%.2f", medico.calcularSalario()));

        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }

        vista.pausar();
    }

}