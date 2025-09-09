public class Ficha {
    private String simbolo;
    private boolean descubierta;
    private boolean emparejada;

    public Ficha(String simbolo) {
        this.simbolo = simbolo;
        this.descubierta = false;
        this.emparejada = false;
    }

    public String getSimbolo() {
        return this.simbolo;
    }

    public boolean estaDescubierta() {
        return this.descubierta;
    }

    public boolean estaEmparejada() {
        return this.emparejada;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public void ocultar() {
        this.descubierta = false;
    }

    public void emparejar() {
        this.emparejada = true;
    }
}
