package MediaCovid;

import Model.InformasiList;
import Model.InformasiProperty;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HPController implements Initializable {
    @FXML Button btnLogin;
    @FXML Button btnAkun;
    @FXML Button btnEXIT;
    @FXML Label AkunName;

    public String Username="";
    
    public InformasiList infoList;
    
    @FXML public TableView<InformasiProperty> tvDataInformasi;
    @FXML TableColumn<InformasiProperty, String> user;
    @FXML TableColumn<InformasiProperty, String> info;
    
    public ObservableList<InformasiProperty> listInfo = FXCollections.observableArrayList();
    Main main = new Main();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getTarget().equals(btnLogin))
        {
            if(AkunName.getText().equals(""))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent login_parent = loader.load();
                Scene login_scene = new Scene(login_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(login_scene);
                app_stage.show();
            }
            else
            {
                System.out.println(AkunName.getText() + " Telah Log Out");
                btnLogin.setText("Log In");
                AkunName.setText("");
            }
            System.out.println("Go to Login Page");
        }
        else if(event.getTarget().equals(btnAkun)){
            System.out.println("This is "+AkunName.getText());
            if(AkunName.getText().equals(""))
            {
                AkunName.setText("Belum Login!!");
            }
            else
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
                Parent Akun_parent = loader.load();
                
                AccountController akunControl = loader.getController();
                akunControl.getUsername(AkunName.getText());
                
                Scene Akun_scene = new Scene(Akun_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(Akun_scene);
                app_stage.show();
            }
        }
        else if(event.getTarget().equals(btnEXIT)){
            System.out.print("[Homepage] Exit The Application");
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoList = new InformasiList();
        infoList.loadXMLFile();
        user.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        info.setCellValueFactory(cellData -> cellData.getValue().getInfoProperty());
        tvDataInformasi.setItems(infoList.get());
        AkunName.setText(Username);
    }
    
    public void getUsername(String user){
        this.Username = user;
        AkunName.setText(user);
        System.out.println("[Homepage] AkunName: "+ AkunName.getText());
        Logged();
    }
    
    public void Logged(){
        System.out.println("[Homepage] Username: "+ Username);
        if(!AkunName.getText().equals(""))
        {
            System.out.println("[Homepage] "+ AkunName.getText()+" Sedang Login");
            btnLogin.setText("Log Out");
        }
    }
}
