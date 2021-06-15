package Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.collections.*;

public class InformasiList {
    private ObservableList<InformasiProperty> listInfo;
    private String InfoXmlFile;

    public InformasiList() {
        listInfo = FXCollections.observableArrayList();
        InfoXmlFile = "InfoXMLFile.xml";
    }
    
    public ObservableList<InformasiProperty> get(){
        return listInfo;
    }
    
    public void setFromArray (Informasi[] array){
        listInfo = FXCollections.observableArrayList();
        
        for(Informasi i: array)
        {
            listInfo.add(new InformasiProperty(i.getUsername(), i.getInfo()));
        }
    }
    
    public void loadXMLFile(){
        try {
            XStream xs = new XStream(new StaxDriver());
            FileInputStream input = new FileInputStream(InfoXmlFile);

            String s = "";
            int i = input.read();

            while (i != -1) {
                s += (char) i;
                i = input.read();
            }

            Informasi[] array = (Informasi[]) xs.fromXML(s);
            this.setFromArray(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Informasi[] getArray(){
        Informasi[] array = new Informasi[listInfo.size()];
        for(int i=0; i<listInfo.size();i++)
        {
            String username = listInfo.get(i).getUsername();
            String info = listInfo.get(i).getInfo();
            array[i] = new Informasi(username,info);
            System.out.println(username+" || "+info);
            System.out.println(array[i]);
        }
        
        return array;
    }
    
    public void saveXMLFile(){
        Informasi[] array = this.getArray();
        XStream xs = new XStream(new StaxDriver());
        String xml = xs.toXML(array);
        System.out.println(xml);
        
        try{
            FileOutputStream output = new FileOutputStream(InfoXmlFile);
            byte[] bytes = xml.getBytes();
            output.write(bytes);
            output.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
