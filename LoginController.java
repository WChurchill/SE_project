public class LoginController {
    private static LoginController instance;

    public static LoginController getInstance(){
	if(instance==null) instance = new LoginController();
	return instance;
    }
    
    private LoginController(){
	
    }

    public void processLogin(){
	LoginDialog dialog = new LoginDialog();
	
	String username = dialog.promptUsername();
	String password = dialog.promptPassword();

	CustomerDB customerDB = CustomerDB.getInstance();
	User user = customerDB.getUser(username);
	if(user == null || !user.verifyPassword(password)){
	    dialog.invalidUser();
	}else if(user instanceof Admin){
	    Admin admin = (Admin)user;
	    admin.startDialog();
	}else{
	    Customer customer = (Customer)user;
	    customer.startDialog();
	}
    }

    public void createAccount(){
	LoginDialog dialog = new LoginDialog();
	String username = dialog.promptUsername();
	String password = dialog.promptPassword();

	if(dialog.confirmEntries()){
	    CustomerDB customerDB = CustomerDB.getInstance();
	    Customer customer = new Customer(username, password);
	    customerDB.addUser(customer);
	    dialog.createSuccess();
	}
	
    }
}
