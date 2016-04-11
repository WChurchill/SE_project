public class User {
    protected String username;
    protected String password; 

    public User(String un, String pwd){
        username=un;
        password=pwd;
    }

    
    public boolean verifyPassword(String testString){
	return password.equals(testString);
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
    
        
    public String toString(){
	return username+"\n"+password;
    }
}
