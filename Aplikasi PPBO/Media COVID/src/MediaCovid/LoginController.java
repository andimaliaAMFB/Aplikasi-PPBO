package MediaCovid;

import Model.DataAkun;
import Model.DataAkunList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    @FXML Label LAdminLog;
    @FXML TextField tfPassword;
    @FXML TextField tfUsername;
    
    @FXML Button btnLogin;
    @FXML Button btnRegist;
    @FXML Button btnHP;
    
    public List<List<String>> unSeenListLogin = new ArrayList();
    DataAkun akun = new DataAkun("","","","");
    
    @FXML private void handleButtonAction(ActionEvent event) throws IOException {
        
        if(event.getTarget().equals(btnLogin)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent HP_parent = loader.load();
            HPController hpControl = loader.getController();
            
            if (tfUsername.getText().equals("admin") || tfUsername.getText().equals("Admin")) 
            {
                if(!akun.AdminPassCek(tfPassword.getText()))
                {
                    LAdminLog.setText("Password Salah");
                }
                else
                {
                    tfUsername.setText("Admin");
                    hpControl.getUsername(tfUsername.getText());
                    Scene HP_scene = new Scene(HP_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(HP_scene);
                    app_stage.show();
                }
            }
            else
            {   
                hpControl.getUsername(tfUsername.getText());
                Scene HP_scene = new Scene(HP_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(HP_scene);
                app_stage.show();
            }
        }
        else if(event.getTarget().equals(btnHP))
        {
            Parent HP_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene HP_scene = new Scene(HP_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(HP_scene);
            app_stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void passedData(String InfoArray, int Array, int Array2){
        unSeenListLogin.add(new ArrayList<>());
        if(Array2 == 0)
            unSeenListLogin.get(Array).add(InfoArray);
        else
            unSeenListLogin.get(Array).add(InfoArray);
    }
}
