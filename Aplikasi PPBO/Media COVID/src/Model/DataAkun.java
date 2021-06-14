package Model;

import Model.Informasi;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataAkun {
    protected SimpleStringProperty username;
    protected SimpleStringProperty password;
    protected SimpleStringProperty email;
    protected SimpleStringProperty notlp;
    
    private Informasi info = new Informasi("","");

    public DataAkun(String username, String password, String email, String notlp) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.notlp = new SimpleStringProperty(notlp);
    }

    public String getUsername() {
        return this.username.get();
    }
    public void setUsername(String username) {
        this.username.set(username);
    }
    

    public String getPassword() {
        return password.get();
    }
    public void setPassword(String password) {
        this.password.set(password);
    }

    
    public String getEmail() {
        return this.email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }

    
    public String getNoTlp() {
        return notlp.get();
    }
    public void setNoTlp(String notlp) {
        this.notlp.set(notlp);
    }
    
    
    public Boolean AdminPassCek(String cek){
        String pass[] = {"admin", "demo"};
        boolean Cek = false;
        for(int i=0;i<2;i++)
        {
            if(cek.equals(pass[i]))
                Cek = true;
        }
        return Cek;
    }
    
    public void sendUsernameInfo (String username){
        info.setUsername(username);
    }
}
