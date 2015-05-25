import java.util.Vector;

import DBClasses.Article;
import DBClasses.BankConnection;
import DBClasses.SEPA;
import DBClasses.User;


public class Client {
    
    private static ClientConnectionManager ccm;
    private static User user;

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	while(true)
	{
        	clearConsole();
        	int menuChoice=LoginMenu();
        	switch(menuChoice)
        	{
        	case 1:
        	    LoginScreen();
        	    MainMenu();
        	    break;
        	case 2:
        	    CreateUserScreen();
        	    break;
        	case 3:
        	    ConfirmCreatedUser();
        	case 4:
        	    return;
        	}
	}

    }
    
    private static void ConfirmCreatedUser() {
	// TODO Auto-generated method stub
	
    }

    private static void CreateUserScreen() {
	while(true)
	{
	    User usr=new User();
	    System.out.println("Please insert your name:");
	    String str=System.console().readLine();
	    usr.setName(str);
	    System.out.println("Insert your surname:");
	    str=System.console().readLine();
	    usr.setSurname(str);
	    System.out.println("Insert your e-Mail (This will also be your username):");
	    str=System.console().readLine();
	    usr.seteMail(str);
	    System.out.println("Insert your adress");
	    System.out.println("Street:");
	    str=System.console().readLine();
	    usr.setStr(str);
	    System.out.println("Nr:");
	    str=System.console().readLine();
	    usr.setNr(str);
	    System.out.println("Plz:");
	    str=System.console().readLine();
	    usr.setPlz(str);
	    System.out.println("City:");
	    str=System.console().readLine();
	    usr.setCity(str);
	    System.out.println("Country:");
	    str=System.console().readLine();
	    usr.setCountry(str);
	    System.out.println("Bank data");
	    System.out.println("(1)SEPA or (2)Bank Coordinates?:");
	    str=System.console().readLine();
	    int i=Integer.parseInt(str);
	    if(i==1)
	    {
		SEPA sepa=new SEPA();
		System.out.println("IBAN:");
		str=System.console().readLine();
		sepa.setIban(str);
		System.out.println("BIC_SWIFT:");
		str=System.console().readLine();
		sepa.setBic_swift(str);
		usr.setBankData(sepa);
	    }
	    else
	    {
		BankConnection bnk=new BankConnection();
		System.out.println("Account Nr.:");
		str=System.console().readLine();
		bnk.setAcctNr(str);
		System.out.println("Bank Nr.:");
		str=System.console().readLine();
		bnk.setBankNr(str);
		System.out.println("Bank Name:");
		str=System.console().readLine();
		bnk.setBankName(str);
		usr.setBankData(bnk);
	    }
	    System.out.println("");
	    System.out.println("(1)Confirm, (2)Repeat or (3)Back");
	    str=System.console().readLine();
	    i=Integer.parseInt(str);
	    switch(i)
	    {
	    case 1:
		messages.NewUserMessageRequest numrMsg=new messages.NewUserMessageRequest(usr);
		try
		{
		ccm.SendMessage(numrMsg);
		}
		catch (ClientConnectionException e)
		{
		    System.out.println(e.getMessage());
			System.out.println("(1) Retry or (2) Back");
			str=System.console().readLine();
			if(Integer.parseInt(str)==2)
			    return;
		}
		break;
	    case 2:
		break;
	    case 3:
		return;
	    }
	}
    }

    private static void LoginScreen() {
	while (true)
	{
	    System.out.println("Please enter your username:");
	    String usr=System.console().readLine();
	    System.out.println("Please enter your password:");
	    String pwd=System.console().readLine();
	    try{
		ccm.Login(usr, pwd);
		return;
	    }
	    catch(ClientConnectionException e)
	    {
		System.out.println(e.getMessage());
		System.out.println("(1) Retry or (2) Back");
		usr=System.console().readLine();
		if(Integer.parseInt(usr)==2)
		    return;
	    }
	}
    }

    private static int LoginMenu()
    {
	clearConsole();
	System.out.println("Please select one of the following options:");
	System.out.println("");
	System.out.println("1) Login existing user");
	System.out.println("2) Create a new user");
	System.out.println("3) Confirm user");
	System.out.println("4) Exit");
	System.out.println("");
	System.out.print("Option: ");
	String readStr=System.console().readLine();
	return Integer.parseInt(readStr);
    }
    
    private static int MainMenu()
    {
	while(true)
	{
	clearConsole();
	System.out.println("Please select one of the following options:");
	System.out.println("");
	System.out.println("1) Edit my User data");
	System.out.println("2) Create new Article");
	System.out.println("3) Edit an article");
	System.out.println("4) View offers");
	System.out.println("5) Logout");
	System.out.println("");
	System.out.print("Option: ");
	String readStr=System.console().readLine();
	int i= Integer.parseInt(readStr);
	switch(i)
	{
	case 1:
	    EditUserMenu();
	    break;
	case 2:
	    AddArticleMenu();
	    break;
	case 3:
	    EditArticleMenu();
	    break;
	case 4:
	    GetOffers();
	    break;
	case 5:
	    Logout();
	    return 0;
	}
	}
    }
    
    private static int EditUserMenu()
    {
	while(true)
	{
	  User usr=new User();
	    System.out.println("Please insert your name:");
	    String str=System.console().readLine();
	    user.setName(str);
	    System.out.println("Insert your surname:");
	    str=System.console().readLine();
	    user.setSurname(str);
	    System.out.println("Insert your e-Mail (This will also be your username):");
	    str=System.console().readLine();
	    user.seteMail(str);
	    System.out.println("Insert your adress");
	    System.out.println("Street:");
	    str=System.console().readLine();
	    user.setStr(str);
	    System.out.println("Nr:");
	    str=System.console().readLine();
	    user.setNr(str);
	    System.out.println("Plz:");
	    str=System.console().readLine();
	    user.setPlz(str);
	    System.out.println("City:");
	    str=System.console().readLine();
	    user.setCity(str);
	    System.out.println("Country:");
	    str=System.console().readLine();
	    user.setCountry(str);
	    
	    System.out.println("");
	    System.out.println("(1)Confirm, (2)Repeat or (3)Back");
	    str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		messages.ChangeUserDataMessageRequest numrMsg=new messages.ChangeUserDataMessageRequest(user);
		try
		{
		ccm.SendMessage(numrMsg);
		}
		catch (ClientConnectionException e)
		{
		    System.out.println(e.getMessage());
			System.out.println("(1) Retry or (2) Back");
			str=System.console().readLine();
			if(Integer.parseInt(str)==2)
			    return 0;
		}
		break;
	    case 2:
		break;
	    case 3:
		return 0;
	    }
	}
    }
    
    private static int AddArticleMenu()
    {
	while(true)
	{
	Article art=new Article(user.getId());
	System.out.println("Please insert a short description:");
	String str=System.console().readLine();
	art.setShortDescription(str);
	System.out.println("Please insert a long description:");
	str=System.console().readLine();
	art.setLongDescription(str);
	System.out.println("Please insert the amount of articles:");
	str=System.console().readLine();
	art.setAmount(Float.parseFloat(str));
	System.out.println("Please insert the unit wieght int kg:");
	str=System.console().readLine();
	art.setWeight(Float.parseFloat(str));
	System.out.println("Please insert the VAT for the articles as %:");
	str=System.console().readLine();
	art.setVat(Float.parseFloat(str));
	System.out.println("Do you want to insert the (1)Gross price or the (2)Net price?");
	str=System.console().readLine();
	if(Integer.parseInt(str)==1)
	{
	    System.out.println("Please insert the Gross Unit Price:");
	    str=System.console().readLine();
	    art.setGrossPrice(Float.parseFloat(str));
	}
	else
	{
	    System.out.println("Please insert Net Unit Price:");
	    str=System.console().readLine();
	    art.setNetPrice(Float.parseFloat(str));
	}
	System.out.println("Please insert the Expiration Date:");
	str=System.console().readLine();
	art.setExpDate(new java.sql.Date(java.sql.Date.parse(str)));
	
	 System.out.println("");
	    System.out.println("(1)Confirm, (2)Repeat or (3)Back");
	    str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		messages.AddArticleMessageRequest aaMsg=new messages.AddArticleMessageRequest(art);
		try
		{
		ccm.SendMessage(aaMsg);
		}
		catch (ClientConnectionException e)
		{
		    System.out.println(e.getMessage());
			System.out.println("(1) Retry or (2) Back");
			str=System.console().readLine();
			if(Integer.parseInt(str)==2)
			    return 0;
		}
		break;
	    case 2:
		break;
	    case 3:
		return 0;
	}
    }
    }
    
    private static int EditArticleMenu()
    {
	int offset=0;
	Vector<Article> artList;
	try{
	    messages.ArticleListMessageAnswer alMsg=(messages.ArticleListMessageAnswer)ccm.SendMessage(new messages.ArticleListMessageRequest(user.getId(),true));
	    artList=(Vector<Article>)alMsg.getPayload();
	}
	catch(ClientConnectionException e)
	{
	    System.out.println(e.getMessage());
	    return 0;
	}
	while (true)
	{
	    PrintOutArticleList(artList,offset);
	    System.out.println();
	    System.out.println("1)Go up");
	    System.out.println("2)Go down");
	    System.out.println("3)Select one article");
	    System.out.println("4)Back");
	    System.out.println();
	    System.out.println("Select one option: ");
	    String str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		offset-=5;
		if(offset<0)
		    offset=0;
		break;
	    case 2:
		offset+=5;
		if(offset>=artList.size())
		    offset=artList.size()-6;
		break;
	    case 3:
		System.out.println("Insert the number of the selected article:");
		str=System.console().readLine();
		EditSpecificArticleMenu(artList.get(Integer.parseInt(str)));
		break;
	    case 4:
		return 0;
	    }
	    
	}
    }
    
    private static void EditSpecificArticleMenu(Article article) {
	while(true)
	{
	    DisplayArticleDetails(article);
	    System.out.println();
	    System.out.println("1) Edit article");
	    System.out.println("2) Back");
	    String str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		Article art=new Article(user.getId());
		System.out.println("Please insert a short description:");
		str=System.console().readLine();
		art.setShortDescription(str);
		System.out.println("Please insert a long description:");
		str=System.console().readLine();
		art.setLongDescription(str);
		System.out.println("Please insert the amount of articles:");
		str=System.console().readLine();
		art.setAmount(Float.parseFloat(str));
		System.out.println("Please insert the unit wieght int kg:");
		str=System.console().readLine();
		art.setWeight(Float.parseFloat(str));
		System.out.println("Please insert the VAT for the articles as %:");
		str=System.console().readLine();
		art.setVat(Float.parseFloat(str));
		System.out.println("Do you want to insert the (1)Gross price or the (2)Net price?");
		str=System.console().readLine();
		if(Integer.parseInt(str)==1)
		{
		    System.out.println("Please insert the Gross Unit Price:");
		    str=System.console().readLine();
		    art.setGrossPrice(Float.parseFloat(str));
		}
		else
		{
		    System.out.println("Please insert Net Unit Price:");
		    str=System.console().readLine();
		    art.setNetPrice(Float.parseFloat(str));
		}
		System.out.println("Please insert the Expiration Date:");
		str=System.console().readLine();
		art.setExpDate(new java.sql.Date(java.sql.Date.parse(str)));
		
		 System.out.println("");
		    System.out.println("(1)Confirm, (2)Repeat or (3)Back");
		    str=System.console().readLine();
		    switch(Integer.parseInt(str))
		    {
		    case 1:
			messages.ChangeAreticleMessageRequest caMsg=new messages.ChangeAreticleMessageRequest(art);
			article=art;
			try
			{
			    ccm.SendMessage(caMsg);
			}
			catch (ClientConnectionException e)
			{
			    System.out.println(e.getMessage());
			    System.out.println("(1) Retry or (2) Back");
			    str=System.console().readLine();
			    if(Integer.parseInt(str)==2)
				return;
			}
			break;
		    case 2:
			break;
		    case 3:
			return;
		    }
		    break;
	    case 2:
		return;
	    }
	}
    }

    private static void DisplayArticleDetails(Article article) {
	System.out.println("Short description: "+article.getShortDescription());
	System.out.println("Long description: "+article.getLongDescription());
	System.out.println("Amount: "+article.getAmount());
	System.out.println("Weight: "+article.getWeight());
	System.out.println("Gross Price: "+article.getGrossPrice());
	System.out.println("VAT: "+article.getVat());
	System.out.println("Net Price: "+article.getNetPrice());
	System.out.println("Exp. Date: "+article.getExpDate());
	System.out.println("=========================================================================");
	
	
    }

    private static void PrintOutArticleList(Vector<Article> artV, int offset)
    {
	PrintOutArticleList(artV,offset,5);
    }
    
    private static void PrintOutArticleList(Vector<Article> artV, int offset, int elemNr)
    {
	System.out.println("=========================================================================");
	for(int i=0;i<elemNr&& (i+offset)<artV.size();i++)
	{
	    System.out.println(i+offset+") "+artV.get(offset+i).getShortDescription()+
		    "  Amount: "+artV.get(offset+i).getAmount()+
		    "  Price: "+artV.get(offset+i).getNetPrice()+" Euro");
	}
	System.out.println("=========================================================================");
    }
    
    private static int GetOffers()
    {
	int offset=0;
	Vector<Article> artList;
	try{
	    messages.ArticleListMessageAnswer alMsg=(messages.ArticleListMessageAnswer)ccm.SendMessage(new messages.ArticleListMessageRequest(user.getId(),false));
	    artList=(Vector<Article>)alMsg.getPayload();
	}
	catch(ClientConnectionException e)
	{
	    System.out.println(e.getMessage());
	    return 0;
	}
	while (true)
	{
	    PrintOutArticleList(artList,offset);
	    System.out.println();
	    System.out.println("1)Go up");
	    System.out.println("2)Go down");
	    System.out.println("3)Select one article");
	    System.out.println("4)Back");
	    System.out.println();
	    System.out.println("Select one option: ");
	    String str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		offset-=5;
		if(offset<0)
		    offset=0;
		break;
	    case 2:
		offset+=5;
		if(offset>=artList.size())
		    offset=artList.size()-6;
		break;
	    case 3:
		System.out.println("Insert the number of the selected article:");
		str=System.console().readLine();
		DisplaySpecificArticleMenu(artList.get(Integer.parseInt(str)));
		break;
	    case 4:
		return 0;
	    }
	    
	}
    }
    
    private static void DisplaySpecificArticleMenu(Article article) {
	while(true)
	{
	    DisplayArticleDetails(article);
	    System.out.println();
	    System.out.println("1) Buy article");
	    System.out.println("2) Back");
	    String str=System.console().readLine();
	    switch(Integer.parseInt(str))
	    {
	    case 1:
		System.out.println("Insert the amount you want to buy:" );
		str=System.console().readLine();
		float amt=Float.parseFloat(str);
		try
		{
		    ccm.SendMessage(new messages.BuyArticleMessageRequest(user.getId(),article,amt));
		}
		catch(ClientConnectionException e)
		{
		    System.out.println(e.getMessage());
		    System.out.println("(1) Retry or (2) Back");
		    str=System.console().readLine();
		    if(Integer.parseInt(str)==2)
			return;
		}
		break;
	    case 2:
		return;
	    }
	}
	
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
