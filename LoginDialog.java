import java.util.Scanner;

public class LoginDialog {
    public String promptUsername(){
	Scanner loginScanner = new Scanner(System.in);	
	System.out.print("Enter username: ");
	return loginScanner.nextLine().trim();
    }

    public String promptPassword(){
	Scanner loginScanner = new Scanner(System.in);
	System.out.print("Enter password: ");
	return loginScanner.nextLine().trim();
    }

    public void invalidUser(){
	System.out.println("Invalid username or password");
    }
    
    public void createSuccess(){
    	System.out.println("Account has been successfully created");
    }

    // returns true if the user wishes to create an account with the given info
    public boolean confirmEntries(){
	Scanner input = new Scanner(System.in);
	String response = null;
	do{
	    System.out.print("Create Account? [y/n]: ");
	    response = input.next().trim().toLowerCase();
	}while(!response.equals("y") && !response.equals("n"));
	return response.equals("y");
    }

    

}
