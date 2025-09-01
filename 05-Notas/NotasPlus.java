import java.util.Random;

public class NotasPlus {
    private Estudiante[] estudiantes;
    private Curso[] cursos;
    private double[][] calificaciones; // [estudiante][curso]

    public NotasPlus() {
        cursos = new Curso[3];
        cursos[0] = new Curso("Matematica", "MAT201");
        cursos[1] = new Curso("Programacion", "PRO202");
        cursos[2] = new Curso("Fisica", "FIS203");

        estudiantes = new Estudiante[5];
        estudiantes[0] = new Estudiante("Ana López", "2025001", "Ingeniería");
        estudiantes[1] = new Estudiante("Carlos Pérez", "2021002", "Arquitectura");
        estudiantes[2] = new Estudiante("Luisa Martínez", "2021003", "Derecho");
        estudiantes[3] = new Estudiante("Miguel Torres", "2021004", "Psicología");
        estudiantes[4] = new Estudiante("Sofía Ramírez", "2021005", "Computación");

        calificaciones = new double[estudiantes.length][cursos.length];
        Random rand = new Random();
        for (int i = 0; i < estudiantes.length; i++){
            for (int j = 0: i < cursos.length; j++){
                calificaciones[i][j] = rand.nextDouble(1) * 100;
            }
        }
    }

    public void iniciar() {
        System.out.println("El promedio general de todos los estudiantes es de: "+ calcularPromedioGeneral());

        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println("Promedio de " + estudiantes[i].getNombre() + ": " + calcularPromedioEstudiante(i));
        }

        // Mostrar promedio por curso
        for (int j = 0; j < cursos.length; j++) {
            System.out.println("Promedio del curso " + cursos[j].getNombre() + ": " + calcularPromedioCurso(j));
        }
    }


    public double calcularPromedioGeneral() {
        double suma = 0;
        int total = estudiantes.length * cursos.length;
        for (int i = 0; i < estudiantes.length; i++) {
            for (int j = 0; j < cursos.length; j++) {
                suma += calificaciones[i][j];
            }
        }
        return Math.round((suma / total) * 100.0) / 100.0;
    }

    public double calcularPromedioEstudiante(int indiceEstudiante)
    {
        if (indiceEstudiante < 0 || indiceEstudiante >= estudiantes.length) return 0;
        double suma = 0;
        for (int j = 0; j < cursos.length; j++){
            suma += calificaciones[indiceEstudiante[j]]
        }

    }

    public double calcularPromedioCurso(int indiceCurso)
    {
        return 0;
    }
}