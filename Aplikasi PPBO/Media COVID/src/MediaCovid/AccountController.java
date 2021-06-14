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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountController implements Initializable {
    private String Username;
    @FXML TextField tfinfo;
    @FXML Button btnHP;
    @FXML Button btnSubmit;
    @FXML Button btnRemove;
    @FXML Text TextTitle;
    @FXML Label DelSistem;
    
    public InformasiList infoList;
    
    @FXML public TableView<InformasiProperty> tvDataInformasi;
    @FXML TableColumn<InformasiProperty, String> user;
    @FXML TableColumn<InformasiProperty, String> info;
    
    public ObservableList<InformasiProperty> listInfo = FXCollections.observableArrayList();
    Main main = new Main();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent HP_parent = loader.load();
        HPController hpControl = loader.getController();
        
        if(event.getTarget().equals(btnSubmit)){
            InformasiProperty informasi = new InformasiProperty();
            informasi.setUsername(Username);
            informasi.setInfo(tfinfo.getText());
            
            tvDataInformasi.getItems().add(informasi);
            infoList.saveXMLFile();
        }
        
        if (event.getTarget().equals(btnRemove)) {
            if(Username.equals("Admin"))
            {
                int picked = tvDataInformasi.getSelectionModel().getSelectedIndex();
                if (picked >= 0) {
                    tvDataInformasi.getItems().remove(picked);
                    System.out.println("[Data yang dipilih telah dihapus]");
                }
                infoList.saveXMLFile();
            }
            else{
                System.out.println(Username+" Try to Delete a Data");
                DelSistem.setText("Hanya Admin yang bisa menghapus Informasi");
            }
        }
        
        if(event.getTarget().equals(btnHP)){
            System.out.println("Go to Homepage");
            
            
            hpControl.getUsername(Username);
            Scene HP_scene = new Scene(HP_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(HP_scene);
            app_stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoList = new InformasiList();
        infoList.loadXMLFile();
        user.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        info.setCellValueFactory(cellData -> cellData.getValue().getInfoProperty());
        tvDataInformasi.setItems(infoList.get());
    }
    
    public void getUsername(String user){
        this.Username = user;
        TextTitle.setText(Username+" ACCOUNT");
    }
}