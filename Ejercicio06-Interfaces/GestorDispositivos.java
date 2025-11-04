import java.util.ArrayList;

public class GestorDispositivos {
    private RepositorioDispositivo repo;
    private ServicioMedicion svcMedicion;
    private ServicioAccion svcAccion;
    private ServicioRegistro svcRegistro;
    
    
    public GestorDispositivos() {
        // Crear el repositorio (donde vivirá la lista polimórfica)
        this.repo = new RepositorioDispositivo();
        
        // Crear los servicios especializados
        this.svcMedicion = new ServicioMedicion();
        this.svcAccion = new ServicioAccion();
        this.svcRegistro = new ServicioRegistro();
        
    }
    
    public void inicializarCatalogo() {
        
        // ==========================================
        // DISPOSITIVOS TIPO 1: SENSORES DE SUELO
        // Implementan: Medible
        // ==========================================
        
        // Sensor 1: Parcela Norte
        repo.agregar(new SensorSuelo(
            "SS-001",                    // ID único
            "Sensor Suelo Norte",        // Nombre descriptivo
            "AgroTech",                  // Fabricante
            0.5,                         // Consumo: 0.5 kWh (bajo)
            "Parcela Norte",             // Ubicación
            "2024-01-15",                // Fecha instalación
            30.0                         // Profundidad: 30 cm
        ));
        
        // Sensor 2: Parcela Sur
        repo.agregar(new SensorSuelo(
            "SS-002",
            "Sensor Suelo Sur",
            "FarmSense",
            0.45,                        // Consumo ligeramente menor
            "Parcela Sur",
            "2024-02-20",
            25.0                         // Menos profundidad
        ));
        
        // ==========================================
        // DISPOSITIVOS TIPO 2: ESTACIONES METEOROLÓGICAS
        // Implementan: Medible, Registrable
        // ==========================================
        
        // Estación 1: Central
        repo.agregar(new EstacionMeteorologica(
            "EM-001",
            "Estación Central",
            "WeatherPro",
            2.5,                         // Consumo medio
            "Centro de la Finca",
            "2023-11-10",
            5.0                          // Altura: 5 metros
        ));
        
        // Estación 2: Este
        repo.agregar(new EstacionMeteorologica(
            "EM-002",
            "Estación Este",
            "ClimaTech",
            2.3,
            "Zona Este",
            "2024-03-05",
            4.5                          // Altura: 4.5 metros
        ));
        
        // ==========================================
        // DISPOSITIVOS TIPO 3: DRONES DE RIEGO
        // Implementan: Accionable, Registrable
        // ==========================================
        
        // Dron Riego 1: Alpha
        repo.agregar(new DronRiego(
            "DR-001",
            "Dron Riego Alpha",
            "AgroDrone",
            5.0,                         // Consumo alto (es un dron)
            "Hangar Principal",
            "2024-01-20",
            50.0,                        // Capacidad tanque: 50 litros
            45                           // Autonomía: 45 minutos
        ));
        
        // Dron Riego 2: Beta
        repo.agregar(new DronRiego(
            "DR-002",
            "Dron Riego Beta",
            "SkyFarm",
            4.8,                         // Un poco menos de consumo
            "Hangar Secundario",
            "2024-04-10",
            45.0,                        // Tanque más pequeño
            40                           // Menos autonomía
        ));
        
        // ==========================================
        // DISPOSITIVO TIPO 4: DRON DE MONITOREO
        // Implementan: Medible, Accionable, Registrable
        // ESTE ES EL MÁS COMPLETO - IMPLEMENTA LAS 3 INTERFACES
        // ==========================================
        
        repo.agregar(new DronMonitoreo(
            "DM-001",
            "Dron Monitor Pro",
            "VisionAir",
            6.5,                         // Consumo más alto (equipo avanzado)
            "Hangar Principal",
            "2024-02-15",
            "4K UHD",                    // Resolución de cámara
            "Multiespectral, Térmico, RGB"  // Tipos de sensores
        ));
        
        // ==========================================
        // DISPOSITIVO TIPO 5: SISTEMA DE RIEGO
        // Implementan: Accionable
        // ==========================================
        
        repo.agregar(new SistemaRiego(
            "SR-001",
            "Sistema Riego Automatizado",
            "IrrigaTech",
            8.0,                         // Consumo muy alto (sistema grande)
            "Zona Central",
            "2023-10-05",
            12,                          // Número de válvulas
            45.0                         // Presión del agua en PSI
        ));
        
        
        // Sensor Climático 1: Radiación Solar
        repo.agregar(new SensorClimatico(
            "SC-001",
            "Sensor Radiación Solar",
            "SolarSense",
            0.8,
            "Torre de Observación",
            "2024-01-30",
            "Radiación Solar (W/m²)"    // Variable que mide
        ));
        
        // Sensor Climático 2: Presión Atmosférica
        repo.agregar(new SensorClimatico(
            "SC-002",
            "Sensor Presión Atmosférica",
            "BaroTech",
            0.6,
            "Estación Central",
            "2024-02-25",
            "Presión Atmosférica (hPa)"  // Variable que mide
        ));
        
        // Aspersor 1: Norte
        repo.agregar(new AspersorInteligente(
            "AI-001",
            "Aspersor Smart Norte",
            "SmartIrri",
            1.5,
            "Parcela Norte",
            "2024-03-15",
            15.0                         // Rango de alcance en metros
        ));
        
        // Aspersor 2: Sur
        repo.agregar(new AspersorInteligente(
            "AI-002",
            "Aspersor Smart Sur",
            "SmartIrri",
            1.5,
            "Parcela Sur",
            "2024-03-20",
            18.0                         // Mayor alcance
        ));
        
    }
    
    public ArrayList<DispositivoAgricola> listarTodosDispositivos() {
        // Delegar al repositorio
        // El repositorio retorna una COPIA de la lista (encapsulación)
        return repo.listar();
    }
    

    public DispositivoAgricola buscarPorId(String id) {
        // Delegar al repositorio
        return repo.buscarPorId(id);
    }
    

    public ArrayList<DispositivoAgricola> buscarPorNombre(String nombre) {
        // Delegar al repositorio
        return repo.buscarPorNombre(nombre);
    }
    
    
    public ArrayList<DispositivoAgricola> ordenarPorConsumo(boolean ascendente) {
        // Decisión basada en el parámetro
        if (ascendente) {
            // Orden de menor a mayor consumo
            return repo.ordenarPorConsumoAsc();
        } else {
            // Orden de mayor a menor consumo
            return repo.ordenarPorConsumoDesc();
        }
    }
    
    
    public String verCapacidades(DispositivoAgricola dispositivo) {
        // VALIDACIÓN: Verificar que el dispositivo no sea null
        if (dispositivo == null) {
            return "Dispositivo no válido";
        }
        
        // StringBuilder es más eficiente que concatenar Strings con +
        // Especialmente cuando construimos textos largos
        StringBuilder capacidades = new StringBuilder();
        
        // ENCABEZADO
        capacidades.append("=== CAPACIDADES DEL DISPOSITIVO ===\n");
        capacidades.append(dispositivo.toString()).append("\n\n");
        
        if (dispositivo instanceof Medible) {
            // CAST: Convertir DispositivoAgricola a Medible
            // Esto nos permite acceder a los métodos de Medible
            Medible medible = (Medible) dispositivo;
            
            // Agregar información sobre medición
            capacidades.append("✓ MEDIBLE\n");
            
            // Delegar al servicio de medición para obtener datos
            capacidades.append("  Tipo: ").append(svcMedicion.tipoDeMedicion(medible)).append("\n");
            capacidades.append("  Última medición: ").append(svcMedicion.obtenerMedicionDe(medible)).append("\n\n");
        }
        
        
        if (dispositivo instanceof Accionable) {
            // CAST a Accionable
            Accionable accionable = (Accionable) dispositivo;
            
            capacidades.append("✓ ACCIONABLE\n");
            capacidades.append("  Acciones disponibles:\n");
            
            // Obtener array de acciones del servicio
            String[] acciones = svcAccion.accionesDisponibles(accionable);
            
            // Recorrer y mostrar cada acción
            for (String accion : acciones) {
                capacidades.append("    - ").append(accion).append("\n");
            }
            capacidades.append("\n");
        }
        
        
        if (dispositivo instanceof Registrable) {
            // CAST a Registrable
            Registrable registrable = (Registrable) dispositivo;
            
            capacidades.append("✓ REGISTRABLE\n");
            
            // Obtener historial del servicio
            String[] historial = svcRegistro.historial(registrable);
            
            capacidades.append("  Registros almacenados: ").append(historial.length).append("\n");
            
            // Si hay registros, mostrar el último
            if (historial.length > 0) {
                capacidades.append("  Último registro: ").append(historial[historial.length - 1]).append("\n");
            }
        }
        
        // Convertir StringBuilder a String y retornar
        return capacidades.toString();
    }
    
    
    public String ejecutarAccionEnDispositivo(String id, String accion) {
        // PASO 1: Buscar el dispositivo
        DispositivoAgricola dispositivo = buscarPorId(id);
        
        // VALIDACIÓN 1: Verificar que existe
        if (dispositivo == null) {
            return "Error: Dispositivo no encontrado";
        }
        
        // VALIDACIÓN 2: Verificar que sea Accionable
        if (!(dispositivo instanceof Accionable)) {
            return "Error: Este dispositivo no puede ejecutar acciones";
        }
        
        // PASO 2: Hacer cast a Accionable
        Accionable accionable = (Accionable) dispositivo;
        
        // PASO 3: Delegar ejecución al servicio
        boolean exito = svcAccion.ejecutar(accionable, accion);
        
        // PASO 4: Construir mensaje de resultado
        if (exito) {
            return "Acción '" + accion + "' ejecutada exitosamente en " + dispositivo.getNombre();
        } else {
            return "Error: La acción '" + accion + "' no es válida para este dispositivo";
        }
    }
    
    public String capturarRegistroDeDispositivo(String id) {
        // PASO 1: Buscar dispositivo
        DispositivoAgricola dispositivo = buscarPorId(id);
        
        // VALIDACIÓN 1: Verificar que existe
        if (dispositivo == null) {
            return "Error: Dispositivo no encontrado";
        }
        
        // VALIDACIÓN 2: Verificar que sea Registrable
        if (!(dispositivo instanceof Registrable)) {
            return "Error: Este dispositivo no puede capturar registros";
        }
        
        // PASO 2: Hacer cast a Registrable
        Registrable registrable = (Registrable) dispositivo;
        
        // PASO 3: Delegar captura al servicio
        String registro = svcRegistro.capturar(registrable);
        
        // PASO 4: Retornar mensaje
        return "Registro capturado: " + registro;
    }
}

