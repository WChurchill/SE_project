


public class Admin {
    private static final String username = "admin";
    private static final String password = "dj4dm1n";
    
    public String getID(){
        return this.username;
    }

    public boolean checkID(String id){
        if (!id.equals(username)){
            return false;
        }
        return true;
    }
    
    public boolean checkPswd(String pswd){
        if (!pswd.equals(password)){
            return false;
        }
        return true;
    }
    
}
