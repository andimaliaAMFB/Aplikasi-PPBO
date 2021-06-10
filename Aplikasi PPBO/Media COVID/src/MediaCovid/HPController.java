package MediaCovid;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HPController implements Initializable {
    @FXML
    Button btnLogin;
    @FXML
    Button btnAkun;
    @FXML
    Button btnEXIT;
    @FXML
    Label AkunName;
    
    public String Username="";
    
    @FXML public TableView<Informasi> tvDataInformasi;
    @FXML TableColumn<Informasi, String> user;
    @FXML TableColumn<Informasi, String> info;
    
    public ObservableList<Informasi> listInfo = FXCollections.observableArrayList(
        new Informasi("Admin", "Hello Everyone"),
        new Informasi("Admin", "This is COVID MEDIA")
        );
    Main main = new Main();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        List<List<String>> unSeenList = new ArrayList();
        Informasi info = new Informasi();
        for (int i = 0; i < tvDataInformasi.getItems().size(); i++) {
            info = tvDataInformasi.getItems().get(i);
            unSeenList.add(new ArrayList<>());
            unSeenList.get(i).add(info.username.get());
            unSeenList.get(i).add(info.info.get());
            
        }
        if(event.getTarget().equals(btnLogin))
        {
            if(AkunName.getText().equals(""))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent login_parent = loader.load();

                LoginController loginControl = loader.getController();
                

                for (int i = 0; i < unSeenList.size(); i++) {
                    for (int j = 0; j < unSeenList.get(i).size(); j++) {
                        //cek apa yang ubah ke List
                        System.out.println("unSeenList.get(" + i + ").get(" + j + ")\n" + unSeenList.get(i).get(j));

                        //hasil list get(i).get(j) = index [i][j]
                        //[0][0] = admin
                        //[0][1] = Hello Everyone
                        //dst
                        loginControl.passedData((String)unSeenList.get(i).get(j),i,j);
                    }
                    System.out.println();
                }
                
                
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

                for (int i = 0; i < unSeenList.size(); i++) {
                    for (int j = 0; j < unSeenList.get(i).size(); j++) {
                        //cek apa yang ubah ke List
                        System.out.println("unSeenList.get(" + i + ").get(" + j + ")\n" + unSeenList.get(i).get(j));

                        //hasil list get(i).get(j) = index [i][j]
                        //[0][0] = admin
                        //[0][1] = Hello Everyone
                        //dst
                        if ((j + 1) < unSeenList.get(i).size()) {
                            akunControl.UnSeenListInfo.add(new Informasi(unSeenList.get(i).get(j), unSeenList.get(i).get(j + 1)));
                        }
                    }
                    System.out.println();
                }
                
                Scene Akun_scene = new Scene(Akun_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(Akun_scene);
                app_stage.show();
            }
        }
        else if(event.getTarget().equals(btnEXIT)){
            System.out.print("Exit The Application");
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setCellValueFactory(new PropertyValueFactory<Informasi, String>("username"));
        info.setCellValueFactory(new PropertyValueFactory<Informasi, String>("info"));
        tvDataInformasi.setItems(listInfo);
        AkunName.setText(Username);
    }
    
    public void getUsername(String user){
        this.Username = user;
        AkunName.setText(user);
        System.out.println(AkunName.getText());
        Logged();
    }
    
    public void Logged(){
        System.out.println("Username: "+ Username);
        if(!AkunName.getText().equals(""))
        {
            System.out.println(AkunName.getText()+" Sedang Login");
            btnLogin.setText("Log Out");
        }
    }
    
    public void getInformation(String user, String info){
        listInfo.add(new Informasi(user,info));
        System.out.println(user+" || "+info);
    }
}
