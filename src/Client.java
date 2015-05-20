
public class Client {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	clearConsole();
	LoginMenu();

    }
    
    private static int LoginMenu()
    {
	clearConsole();
	System.out.println("Please select one of the following options:");
	System.out.println("");
	System.out.println("1) Login existing user");
	System.out.println("2) Create a new user");
	System.out.println("3) Exit");
	System.out.println("");
	System.out.print("Option: ");
	String readStr=System.console().readLine();
	return 0;
    }
    
    private static int MainMenu()
    {
	return 0;
    }
    
    private static int EditUserMenu()
    {
	return 0;
    }
    
    private static int AddArticleMenu()
    {
	return 0;
    }
    
    private static int EditArticleMenu()
    {
	return 0;
    }
    
    private static int GetOffers()
    {
	return 0;
    }
    
    private static int Logout()
    {
	return 0;
    }
    
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
