import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class AdminDialog {
    private final int ADD_SONG = 1;
    private final int EDIT_SONG = 2;
    private final int DELETE_SONG = 3;
    private final int SEARCH_SONG = 4;
    private final int SEARCH_CUSTOMER = 5;
    private final int VIEW_CUSTOMER = 6;
    private final int LOGOUT = 7;

    private static AdminDialog instance;
    public static AdminDialog getInstance(){
	if(instance==null) instance = new AdminDialog();
	return instance;
    }

    private AdminDialog(){
	
    }

    private void printMenu(){
	System.out.println(ADD_SONG+") Add Song");
	System.out.println(EDIT_SONG+") Edit Song");
	System.out.println(DELETE_SONG+") Delete Song");
	System.out.println(SEARCH_SONG+") Search Songs");
	System.out.println(SEARCH_CUSTOMER+") Search Customers");
	System.out.println(VIEW_CUSTOMER+") View Customer");
	System.out.println(LOGOUT+") Logout");
	
	System.out.print(">>>Enter Choice: ");      //Prompt user for choice
    }
    
    public void mainLoop(){
	Scanner input = new Scanner(System.in);
	while(true){         //Display main menu and take choice
	    printMenu();         //Display main menu
	    try{
		int choice = input.nextInt();       //Take as choice
            
		switch (choice) {       //switch case to process main menu choice
		case ADD_SONG:
		    //addController(main, input);      //call addController passing input scanner and Artists
		    AddController add = new AddController();
		    add.add();
		    break;
		case EDIT_SONG:
		    //editController(main, input);     //call editController passing input scanner and Artists
		    EditController edit = new EditController();
		    edit.edit();
		    break;
		case DELETE_SONG:
		    //deleteController(main, input);   //call deleteController passing input scanner and Artists 
		    DeleteController delete = new DeleteController();
		    delete.delete();
		    break;
		case SEARCH_SONG:
		    //searchController(main, input);   //call searchController passing input scanner and Artists
		    SearchController search = new SearchController();
		    search.search();
		    break;
		case SEARCH_CUSTOMER:
                    System.out.println("Not Implemented");
		    break;
		case VIEW_CUSTOMER:
                    HistoryController history=new HistoryController();
		    break;
		case LOGOUT:
		    return;
		default:
		    throw new InputMismatchException();
		}
	    }catch (InputMismatchException e) {
		System.out.println("Invalid Choice");
	    }
	    catch(NoSuchElementException e){
		System.out.println("Please enter a number.");
	    }
	}
    }
}
