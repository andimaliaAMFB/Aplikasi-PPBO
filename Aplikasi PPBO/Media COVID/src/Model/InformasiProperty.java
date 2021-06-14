package Model;
import javafx.beans.property.*;

public class InformasiProperty {
    protected SimpleStringProperty username;
    protected SimpleStringProperty info;
    
    public InformasiProperty(){
        this("","");
    }
    public InformasiProperty(String username, String info) {
        this.username = new SimpleStringProperty(username);
        this.info = new SimpleStringProperty(info);
    }
    

    public String getUsername() {
        return this.username.get();
    }
    public StringProperty getUsernameProperty() {
        return username;
    }
    public void setUsername(String username) {
        this.username.set(username);
    }
    

    public String getInfo() {
        return info.get();
    }
    public StringProperty getInfoProperty() {
        return info;
    }
    public void setInfo(String info) {
        this.info.set(info);
    }
}
