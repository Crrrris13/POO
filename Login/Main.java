public  class Main {

    public static void main(String[] args) {
//        View vista = new View();
//        AuthController authController = new AuthController(vista);
//
//        controller.start();

        Admin a = new Admin("Cris", "Cr!s13");
        System.out.println(a.getMenu());

        RegularUser r = new RegularUser("User", "Us3r");
        System.out.println(r.getMenu());
    }
}