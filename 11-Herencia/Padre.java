public class Padre {
    private int privado;
    protected int protegido;
    public int publico;

    public Padre() {
        this.privado = 10;
        this.protegido = 20;
        this.publico = 30;
    }

    public void saludar() {
        System.out.println("Hola");
    }
}