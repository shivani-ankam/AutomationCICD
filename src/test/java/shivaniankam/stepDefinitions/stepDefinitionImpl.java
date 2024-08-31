package shivaniankam.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shivaniankam.TestComponents.BaseTest;
import shivaniankam.pageobjects.CartPage;
import shivaniankam.pageobjects.CheckoutPage;
import shivaniankam.pageobjects.ConfirmationPage;
import shivaniankam.pageobjects.LandingPage;
import shivaniankam.pageobjects.ProductCatalog;

public class stepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		 landingPage = launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		 productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("I add product (.+) to Cart")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@And("Checkout (.+) and submit the order")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		 confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationpage(String string) {
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
		
	}
	

}
