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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountController implements Initializable {
    private String Username;
    @FXML TextField tfinfo;
    @FXML Button btnHP;
    @FXML Button btnSubmit;
    @FXML Button btnRemove;
    @FXML Button btnEdit;
    @FXML Text TextTitle;
    @FXML Label Alern;
    
    public InformasiList infoList;
    
    @FXML public TableView<InformasiProperty> tvDataInformasi;
    @FXML TableColumn<InformasiProperty, String> user;
    @FXML TableColumn<InformasiProperty, String> info;
    
    public ObservableList<InformasiProperty> listInfo = FXCollections.observableArrayList();
    Main main = new Main();
    
    @FXML
    private void SubmitAction(ActionEvent event) throws IOException {
        InformasiProperty informasi = new InformasiProperty();
        informasi.setUsername(Username);
        informasi.setInfo(tfinfo.getText());

        tvDataInformasi.getItems().add(informasi);
        infoList.saveXMLFile();
    }

    @FXML
    private void RemoveAction(ActionEvent event) throws IOException {
        ObservableList<InformasiProperty> informasiSelect;
        informasiSelect = tvDataInformasi.getSelectionModel().getSelectedItems();
        System.out.println("[Dashboard] Username: " + informasiSelect.get(0).getUsername() + "\n[Username]: " + Username);
        int picked = tvDataInformasi.getSelectionModel().getSelectedIndex();
        if (Username.equals("Admin")) {
            if (picked >= 0) {
                tvDataInformasi.getItems().remove(picked);
                System.out.println("[Dasboard] [Admin Telah menghapus Data yang dipilih]");
            }
        } else if (informasiSelect.get(0).getUsername().equals(Username)) {
            if (picked >= 0) {
                tvDataInformasi.getItems().remove(picked);
                System.out.println("[Dasboard] [Data yang dipilih telah dihapus]");
            }
        } else {
            System.out.println("[Dasboard] !! " + Username + " Try to Delete a Data !!");
            Alern.setText("Anda Bukan Penulis Informasi ataupun Admin");
        }
        infoList.saveXMLFile();

    }

    @FXML
    private void EditAction(ActionEvent event) throws IOException {
    }
    
    @FXML
    private void EditedData(TableColumn.CellEditEvent<InformasiProperty, String> editInfo){
        InformasiProperty info = tvDataInformasi.getSelectionModel().getSelectedItem();
        ObservableList<InformasiProperty> informasiSelect;
        informasiSelect = tvDataInformasi.getSelectionModel().getSelectedItems();
        System.out.println("[Dashboard] Username: " + informasiSelect.get(0).getUsername() + "\n[Username]: " + Username);
        int picked = tvDataInformasi.getSelectionModel().getSelectedIndex();
        if (Username.equals("Admin")) {
            if (picked >= 0) {
                info.setInfo(editInfo.getNewValue());
                System.out.println("[Dasboard] [Admin Telah mengedit Data yang dipilih]");
            }
        } else if (informasiSelect.get(0).getUsername().equals(Username)) {
            if (picked >= 0) {
                info.setInfo(editInfo.getNewValue());
                System.out.println("[Dasboard] [Data yang dipilih telah diedit]");
            }
        } else {
            System.out.println("[Dasboard] !! " + Username + " Try to Edit a Data !!");
            Alern.setText("Anda Bukan Penulis Informasi ataupun Admin");
        }
        infoList.saveXMLFile();
    }

    @FXML
    private void HPAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent HP_parent = loader.load();
        HPController hpControl = loader.getController();
        System.out.println("[Dasboard] Go to Homepage");

        hpControl.getUsername(Username);
        Scene HP_scene = new Scene(HP_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(HP_scene);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoList = new InformasiList();
        infoList.loadXMLFile();
        user.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        info.setCellValueFactory(cellData -> cellData.getValue().getInfoProperty());
        tvDataInformasi.setItems(infoList.get());
        
        tvDataInformasi.setEditable(true);
        info.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    public void getUsername(String user){
        this.Username = user;
        TextTitle.setText(Username+" ACCOUNT");
    }
}