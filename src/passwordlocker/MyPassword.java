/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

/**
 *
 * @author Rajesh
 */
public class MyPassword {
    private String username;
    private String password;
    private String description;
    private String createDate;
    private String createTime;
    private String lastChangeDate;
    private String lastChangeTime;
    
    public MyPassword(){
        
    }
    
    public MyPassword(String username, String password, String description, String createDate, String createTime, String lastChangeDate, String lastChangeTime){
        this.username = username;
        this.password = password;
        this.description = description;
        this.createDate = createDate;
        this.createTime = createTime;
        this.lastChangeDate = lastChangeDate;
        this.lastChangeTime = lastChangeTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    @Override
    public boolean equals(Object obj) {
        MyPassword mypassword = (MyPassword) obj;
        if (this.username.equals(mypassword.getUsername()) && this.password.equals(mypassword.getPassword()) && this.description.equals(mypassword.getDescription())) {
            return true;
        } else {
            return false;//To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
    
}
