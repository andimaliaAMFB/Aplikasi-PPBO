package MediaCovid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    Label LAdminLog;
    @FXML
    TextField tfPassword;
    @FXML
    TextField tfUsername;
    
    @FXML
    Button btnLogin;
    @FXML
    Button btnHP;
    
    DataAkun akun = new DataAkun("","");
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent HP_parent = loader.load();
        
        if(event.getTarget().equals(btnLogin)){
            if (tfUsername.equals("admin")) 
            {
                if(akun.AdminPassCek(tfPassword.getText()))
                {
                    LAdminLog.setText("Password Salah");
                }
            }
            else
            {
                HPController hpControl = loader.getController();
                hpControl.getUsername(tfUsername.getText());
                Scene HP_scene = new Scene(HP_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(HP_scene);
                app_stage.show();
            }
        }
        else if(event.getTarget().equals(btnHP))
        {
            Scene HP_scene = new Scene(HP_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(HP_scene);
            app_stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
