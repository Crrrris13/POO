public class Validador {
    public boolean coordenadaValida(int fila, int columna, int maxFilas, int maxColumnas) {
        return fila >= 0 && fila < maxFilas && columna >= 0 && columna < maxColumnas;
    }

    public boolean esNumeroEntero(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
