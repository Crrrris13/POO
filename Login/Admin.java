public class Admin extends User{

    public Admin(String username, String password){
        super(username, password);
    }

    @Override
    public String getMenu(){
        return """
                ===== Admin Menu =====
                1. Manage Users
                2. View Reports
                3. System Settings
                4. Logout
                =======================
                """;
    }

}