package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() 
	{
		try{
		logger.info("****** Starting TC001_Account Registration Test *****");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link....");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link.....");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		logger.info("Providing Customer Details.....");
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
		regpage.setTelephone(randomeNumber());

		String password = randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);

		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message....");
		String confmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");	

		// Manual exception to trigger the catch block for testing
        //if (true) throw new Exception("Manual Exception for Testing");

		}catch(Exception e)
		{
			logger.error("Test Failed.....",e);
			logger.debug("Debug Logs....");
			Assert.fail();
		}
		logger.info("****** Finished TC001_Account Registration Test *****");
	}	
}
