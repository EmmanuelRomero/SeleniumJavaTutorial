package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageLogon {
	private WebDriver driver;
	private By tittleText;
	public PageLogon(WebDriver driver) {
		this.driver = driver;
		tittleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b");
	}
	public void AssertLogonPage() {
		Assert.assertTrue(driver.findElement(tittleText).getText().contains("Welcome back to Mercury Tours"));
	}
}
