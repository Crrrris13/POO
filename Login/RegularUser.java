public class RegularUser extends User{

    public RegularUser(String username, String password){
        super(username, password);
    }

    @Override
    public String getMenu(){
        return """
                ===== RegularUser Menu =====
                1. Manage Users
                2. View Reports
                4. Logout
                =======================
                """;
    }

}