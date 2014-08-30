/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

/**
 *
 * @author Rajesh
 */
public class Log {
    private String username;
    private String date;
    private String time;
    private String description;

    public Log(){
        
    }
    
    public Log(String username, String date, String time, String description){
        this.username = username;
        this.date = date;
        this.time = time;
        this.description = description;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
