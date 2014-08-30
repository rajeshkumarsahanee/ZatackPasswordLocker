/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

/**
 *
 * @author Rajesh
 */
public class User {
    private String name;
    private String username;
    private String password;
    private String lastLoginDate;
    private String lastLoginTime;
    private String filePath;

    public User(){
    }
    
    public User(String name,String username,String password,String lastLoginDate,String lastLoginTime,String filePath){
        this.name = name;
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
        this.filePath = filePath;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        if(this.username.equals(user.getUsername())){
            return true;
        }
        return false;
    }

     
}
