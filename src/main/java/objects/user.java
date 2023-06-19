package objects;

public class user {
    private String account;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private Double content;
    private int age;

    public user(){}
    user(String name, String last_name, int age){
        this.name = name;
        this.lastname = last_name;
        this.username = name;
        this.password = "123";
        this.content = 0.0;
        final String account = name+last_name+age;
        this.account = account.replaceAll("\\s", "");
    }
    user(String username, String password){
        String account = username+password;
        this.content = 0.0;
        this.account = account.replaceAll("\\s", "");
    }


    public void increase(double cash){
        this.content += Math.abs(cash);
    }

    public void decrease(double cash){
        this.content -= Math.abs(cash);
    }


    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setLast_name(String last_name) {
        this.lastname = last_name;
    }

    public String getLast_name() {
        return lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getContent() {
        return content;
    }

    public void setContent(Double content) {
        this.content = content;
    }
}
