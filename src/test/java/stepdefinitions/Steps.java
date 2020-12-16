package stepdefinitions;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;
import cucumber.api.java.en.*;

public class Steps extends BaseClass {

	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Driversfolder/chromedriver.exe");	    
		driver=new ChromeDriver();
	    lp=new LoginPage(driver);
	
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	    // Write code here that turns the phrase above into concrete actions
	   driver.get(url);		
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	    // Write code here that turns the phrase above into concrete actions
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    lp.clickLogin();
	    Thread.sleep(3000);
		
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
	    // Write code here that turns the phrase above into concrete actions
	    if (driver.getPageSource().contains("Login was unsuccessful"))
	    {
	    	Assert.assertTrue(false);
	    }else{
		Assert.assertEquals(title, driver.getTitle());	
	    }
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
	    // Write code here that turns the phrase above into concrete actions
	   driver.close();
	   driver.quit();
	}
	//Customer feature step definitions---------------------------------------------------------------------
	
	@Then("User can view Dashboad")
	public void user_can_view_Dashboad() {
	 addCust= new AddCustomerPage(driver); 
	 Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu(); 
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		
		addCust.clickOnAddnew(); 
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
	   Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
//	    String email=randomstring()+"gmail.com";
	    String email="hsdhjsd12@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Kumar");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing.........");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
	    addCust.clickOnSave();
	    Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
	   Assert.assertTrue(message, addCust.ldriver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}
	//Searching customer by email ID.............................
		@When("Enter customer EMail")
		public void enter_customer_EMail() {
			searchCust=new SearchCustomerPage(driver);
	//		logger.info("********* Searching customer details by Email **************");
			searchCust.setEmail("victoria_victoria@nopCommerce.com");
		}

		@When("Click on search button")
		public void click_on_search_button() throws InterruptedException {
			searchCust.clickSearch();
			Thread.sleep(3000);
		}

		@Then("User should found Email in the Search table")
		public void user_should_found_Email_in_the_Search_table() {
			boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
			Assert.assertEquals(true, status);
		}
		
		//steps for searching a customer by Name................
			@When("Enter customer FirstName")
			public void enter_customer_FirstName() {
			//	logger.info("********* Searching customer details by Name **************");
				searchCust=new SearchCustomerPage(driver);
				searchCust.setFirstName("Victoria");
			}

			@When("Enter customer LastName")
			public void enter_customer_LastName() {
				searchCust.setLastName("Terces");
			}

			@Then("User should found Name in the Search table")
			public void user_should_found_Name_in_the_Search_table() {
				boolean status=searchCust.searchCustomerByName("Victoria Terces");
				Assert.assertEquals(true, status);
			}
}
