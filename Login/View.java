import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    public void showMainMenu(){
        System.out.println("""
                ===== Main Menu =====
                1. Registro
                2. Login
                3. Salir
                =====================""");
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public int askOption(String text){
        System.out.print(text);
        int choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }
}