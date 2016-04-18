public class CheckoutController {
    private static CheckoutController instance;
    
    public static CheckoutController getInstance(){
	if(instance==null) instance = new CheckoutController();
	return instance;
    }
    
    private CheckoutController(){
	
    }
}
