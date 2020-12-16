package stepdefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    
   // Created for generating random string for unique email id
    public String randomstring(){
    	String  generatedstring1=RandomStringUtils.randomAlphabetic(5);
    	return generatedstring1;
    }
    
}
