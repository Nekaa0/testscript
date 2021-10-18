package rebrand.chakaent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChakaAssesment {
	private WebDriver wd;
	private JavascriptExecutor js;
	private SoftAssert sa;
	
	
	@BeforeMethod(alwaysRun = true)
	public  void loginTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		 wd = new ChromeDriver();
		 js = (JavascriptExecutor) wd;
		 sa = new SoftAssert();
		// Open page
		String urL = "https://rebrand.chakaent.com/login";
		wd.get(urL);
		wd.manage().window().maximize();
		// Provide email
		WebElement email = wd.findElement(By.xpath(
				"//app-root/app-authentication[@class='ng-star-inserted']//app-login[@class='ng-star-inserted']//form/input[@type='text']"));
		email.sendKeys("wo@yopmail.com");
		// Provide password
		WebElement password = wd.findElement(By.xpath(
				"//app-root/app-authentication[@class='ng-star-inserted']//app-login[@class='ng-star-inserted']//form/input[@type='password']"));
		password.sendKeys("Password1");
		// Click on CTA to submit
		WebElement submit = wd.findElement(By.xpath(
				"//body/app-root/app-authentication[@class='ng-star-inserted']//app-login[@class='ng-star-inserted']//form//button[@type='submit']"));
		submit.click();
		// verify login

		Thread.sleep(3000);
		String expectedUrl = "https://rebrand.chakaent.com/prefrence";
		String actualUrl = wd.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Url is not as expected");
		WebElement checkIcon = wd.findElement(By.xpath(
				"//app-root/app-app-prefrence[@class='ng-star-inserted']//a[@href='/dashboard']/img[@alt='chaka logo']"));
		Assert.assertTrue(checkIcon.isDisplayed(), "Login in didnt work niyen ");
		checkIcon.click();
		// close modal
		Thread.sleep(3000);
		WebElement modal = wd.findElement(By.xpath(
				"//body/app-root/app-equity-dashboard[@class='ng-star-inserted']/div[@class='equity-container']//app-browse-by-category[@class='ng-star-inserted']/ngx-smart-modal//div[@role='dialog']//button[@class='cbt-primary mb-2']"));
		modal.click();
	}

	
	public  void selectBankTest() throws InterruptedException {
		
	
		Thread.sleep(2000);
		WebElement bank = wd.findElement(By.xpath(
				"//body/app-root/app-equity-dashboard[@class='ng-star-inserted']/div[@class='equity-container']/div[@class='equity-main-container']//app-browse-by-category[@class='ng-star-inserted']//a[@href='/dashboard/country']"));
		bank.click();
		js.executeScript("document.querySelector('.d-lg-block.d-none').scrollTop=5000");
		Thread.sleep(2000);
		WebElement country = wd.findElement(By.linkText("Nigeria"));
		country.click();
		Thread.sleep(2000);
		js.executeScript("document.querySelector('.d-lg-block.d-none').scrollTop=-5000");
		WebElement country2 = wd.findElement(By.linkText("Canada"));
		country2.click();
	}

	@Test
	public  void selectStockTest() throws InterruptedException {
		
	
		//wd.navigate().to("https://rebrand.chakaent.com/dashboard");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		WebElement losers = wd.findElement(
				By.cssSelector("[class='popular-stock-container mt-5 border-bottom py-2 px-1'] .show-more-text"));
		losers.click();
		WebElement stock = wd.findElement(By.cssSelector("div:nth-of-type(3) > .top-gainer-table-content-symbol"));
		stock.click();
		Thread.sleep(2000);
		WebElement months = wd.findElement(By.linkText("6 Months"));
		months.click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		WebElement peRatio = wd.findElement(By.cssSelector("div:nth-of-type(3) > .d-flex.mb-2 > div:nth-of-type(1) > .align-items-center.d-flex.flex-1.justify-content-start.statistics-amount > .amount-text.text-right"));
		String per = peRatio.getText();
		String per2 = "$9.04";
		Assert.assertEquals(per2, per);
		
		WebElement markCap = wd.findElement(By.cssSelector("div:nth-of-type(5) > .d-flex.mb-2 > div:nth-of-type(1) > .align-items-center.d-flex.flex-1.justify-content-start.statistics-amount > .amount-text.text-left"));
    String mc = markCap.getText();
    String mc1 = "$614,191,050.00";
    Assert.assertEquals(mc1, mc);
    
    WebElement dividend = wd.findElement(By.cssSelector("div:nth-of-type(4) > .d-flex.mb-2 > div:nth-of-type(2) > .align-items-center.d-flex.flex-1.justify-content-start.statistics-amount > .amount-text.text-left"));
    String div = "$12.93";
    Assert.assertEquals(div, dividend.getText());
    
    WebElement stockPrice = wd.findElement(By.cssSelector(".border-bottom2.current-bid-value.py-2.text-center"));
    String stkPrice = "$6.34";
    Assert.assertEquals(stkPrice, stockPrice.getText());
    

	}
	@AfterMethod(alwaysRun = true)
	public void close() {
		wd.quit();
		
	}

}
