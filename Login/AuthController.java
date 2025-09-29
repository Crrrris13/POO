import java.util.ArrayList;

public class AuthController {
    private ArrayList<User> users = new ArrayList<>();
    private View view;
    private boolean run;

    public AuthController(){
        this.view = view;
        this.run = true;
    }

    public void start(){
        while (this.run){
            view.showMainMenu();
            int choice = view.askOption("Selecciona una opcion: ");

            switch (choice){
                case 1:
                    this.register();
                    break;

                case 2:
                    this.login();
                    break;

                case 3:
                    this.run = false;
                    break;

                default:
                    this.view.showMessage("Opcion invalida");
                    break;
            }

        }
    }

    public void register(){
        String username = this.view.askUsername();
        String password = this.view.askPassword();
        int role = this.view.askRole();
        
        if (role == 1){
            User a = new Admin(username, password);
        } else if (role == 2) {
            
        }
    }

    public void login(){
        System.out.println("Seleccionaste iniciar sesion");
    }

}