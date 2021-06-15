package Model;

import Model.Informasi;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataAkun {
    protected String username;
    protected String password;
    protected String email;
    protected String notlp;

    public DataAkun() {
    }
    public DataAkun(String username, String password, String email, String notlp) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.notlp = notlp;
    }

    
    public String getUsername() {
        return username;
    }
    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getEmail() {
        return email;
    }
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getNotlp() {
        return notlp;
    }
    @XmlElement
    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }
    
    @Override
    public String toString(){
        return "DataAkun{" +
               "username="+username+
               "password="+password+
               "email="+email+
               "notlp="+notlp+"}";
    }
    
    public boolean PassCek(String username, String password){
        boolean userIspass = false;
        
        if (username.equals("admin")) {
            username = "Admin";
        }
        
        if (username.equals("Admin")) {
            // print user object
            System.out.println("\n(username)" + username);

            if (password.equals("admin")||password.equals("demo")) {
                // print password object
                System.out.println("\n(username)" + username);
                userIspass = true;
            } else {
                System.out.println("Password Salah");
            }
        }
        else
            userIspass = true;
        
        return userIspass;
    }
}
