package Model;

public class RegUser {
    private String name;
    private String cell_phone;
    private String password;
    private String email;

    public RegUser(String userName, String password, String phone, String email) {
        this.name = userName;
        this.cell_phone = phone;
        this.password = password;
       this.email = email;
    }

}
