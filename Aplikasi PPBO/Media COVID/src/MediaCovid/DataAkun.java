package MediaCovid;

public class DataAkun {
    public String username, password;
    
    private Informasi info = new Informasi("","");

    public DataAkun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean AdminPassCek(String cek){
        String pass[] = {"admin", "demo"};
        boolean Cek = false;
        for(int i=0;i<2;i++)
        {
            if(cek.equals(pass[i]))
                Cek = true;
        }
        return Cek;
    }
    
    public void sendUsernameInfo (String username){
        info.setUsername(username);
    }
}
