package daomain;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 18:47
 */
public class Manager {
    private String username;
    private String password;
    
    public Manager() {}
    
    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
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
}
