public class Admin extends User {
    private static final String username = "admin";
    private static final String password = "dj4dm1n";
    private static Admin instance;
    
    public static Admin getInstance(){
	if(instance==null) instance = new Admin();
	return instance;
    }
    
    private Admin(){
	super(username, password);
    }

    public void startDialog(){
	AdminDialog dialog = AdminDialog.getInstance();
	dialog.mainLoop();
    }
}
