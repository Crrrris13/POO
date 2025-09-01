public class Celda {

    private boolean disparada;
    private boolean tieneBarco;

    public Celda() {
        this.disparada = false;
        this.tieneBarco = false;
    }

    public boolean estaDisparada() {
        return disparada;
    }

    public void setDisparada() {
        this.disparada = true;
    }

    public boolean TieneBarco() {
        return tieneBarco;
    }

    public void setTieneBarco(){
        this.tieneBarco = true;
    }

    public String toString() {
        if (!this.disparada) {
            return ".";
        } else {
            return this.tieneBarco ? "X" : "O";
        }
    }
}