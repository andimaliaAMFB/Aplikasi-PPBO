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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private Informasi info;
    @FXML public TableView<Informasi> tvSeenTable;
    @FXML TableColumn<Informasi, String> SeenUser;
    @FXML TableColumn<Informasi, String> SeenInfo;
    public ObservableList<Informasi> UnSeenListInfo = FXCollections.observableArrayList();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent HP_parent = loader.load();
        HPController hpControl = loader.getController();
        List<List<String>> unSeenList = new ArrayList();
        Informasi info = new Informasi();
        for (int i = 0; i < tvSeenTable.getItems().size(); i++) {
            info = tvSeenTable.getItems().get(i);
            unSeenList.add(new ArrayList<>());
            unSeenList.get(i).add(info.username.get());
            unSeenList.get(i).add(info.info.get());
        }
        
        if(event.getTarget().equals(btnSubmit)){
            //hpControl.getInformation(Username,tfinfo.getText());//same function
            info = new Informasi(Username,tfinfo.getText());
            tvSeenTable.getItems().add(info);
        }
        
        if (event.getTarget().equals(btnRemove)) {
            if(Username.equals("Admin"))
            {
                ObservableList<Informasi> AllList, SelectedList;
                AllList = tvSeenTable.getItems();
                SelectedList = tvSeenTable.getSelectionModel().getSelectedItems();
                SelectedList.forEach(AllList::remove);

                System.out.println("Now you only got [" + tvSeenTable.getItems().size() + "] Data");
                System.out.println(Username + " Try to Delete this Data");
                DelSistem.setText("Delete Data Success");

                tvSeenTable.setItems(AllList);
            }
            else
            {
                DelSistem.setText("You're Not Admin!!");
                System.out.println("You're Not Admin!!");
            }
        }
        
        if(event.getTarget().equals(btnHP)){
            for(int i=2;i<unSeenList.size();i++)
            {
                for(int j=0;j<unSeenList.get(i).size();j++)
                {
                    //cek apa yang ubah ke List
                    //System.out.println("unSeenList.get("+i+").get("+j+")\n"+unSeenList.get(i).get(j));
                    
                    //hasil list get(i).get(j) = index [i][j]
                    //[0][0] = admin
                    //[0][1] = Hello Everyone
                    //dst
                    if((j+1) <unSeenList.get(i).size())
                        hpControl.listInfo.add(new Informasi(unSeenList.get(i).get(j),unSeenList.get(i).get(j+1)));
                }
                System.out.println();
            }
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
        SeenUser.setCellValueFactory(new PropertyValueFactory<Informasi, String>("username"));
        SeenInfo.setCellValueFactory(new PropertyValueFactory<Informasi, String>("info"));
        tvSeenTable.setItems(UnSeenListInfo);
    }
    
    public void getUsername(String user){
        this.Username = user;
        TextTitle.setText(Username+" ACCOUNT");
    }
    
    public void setInformation(String info){
        
        tfinfo.setText(info);
        System.out.println(tfinfo.getText());
    }

}