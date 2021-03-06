package Model;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

public class Informasi {
    public SimpleStringProperty username;
    public SimpleStringProperty info;
    
    public Informasi(){
        this("","");
    }
    public Informasi(String username, String info) {
        this.username = new SimpleStringProperty(username);
        this.info = new SimpleStringProperty(info);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getInfo() {
        return info.get();
    }

    public void setInfo(String info) {
        this.info = new SimpleStringProperty(info);
    }
}
