package MediaCovid;

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
    
    public List<List<String>> unSeenListLogin = new ArrayList();
    DataAkun akun = new DataAkun("","");
    
    @FXML private void handleButtonAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent HP_parent = loader.load();
        HPController hpControl = loader.getController();
        
        for (int i = 2; i < unSeenListLogin.size(); i++) {
            for (int j = 0; j < unSeenListLogin.get(i).size(); j++) {
                //cek apa yang ubah ke List
                System.out.println("unSeenList.get(" + i + ").get(" + j + ")\n" + unSeenListLogin.get(i).get(j));

                //hasil list get(i).get(j) = index [i][j]
                //[0][0] = admin
                //[0][1] = Hello Everyone
                //dst
                if ((j + 1) < unSeenListLogin.get(i).size()) {
                    hpControl.listInfo.add(new Informasi(unSeenListLogin.get(i).get(j), unSeenListLogin.get(i).get(j + 1)));
                }
            }
            System.out.println();
        }
        
        if(event.getTarget().equals(btnLogin)){
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
    
    public void passedData(String InfoArray, int Array, int Array2){
        unSeenListLogin.add(new ArrayList<>());
        if(Array2 == 0)
            unSeenListLogin.get(Array).add(InfoArray);
        else
            unSeenListLogin.get(Array).add(InfoArray);
    }
}
