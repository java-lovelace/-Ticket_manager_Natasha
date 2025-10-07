import config.ConfigDB;
import view.LoginRegister;

public class Main {
    public static void main(String ... args){
        ConfigDB.openConnection();
        ConfigDB.closeConnection();
        LoginRegister.LogOut();
    }
}
