public class Hijo extends Padre {
    // protected int protegido;
    // public int publico;

    public void test() {
        System.out.println(protegido);
        System.out.println(publico);
        // System.out.println(privado);
    }
    @Override
    public void saludar() {
        System.out.println("Que putas");
    }
}